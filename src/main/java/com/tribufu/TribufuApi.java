// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tribufu.generated.ApiClient;
import com.tribufu.generated.api.TribufuGeneratedApi;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Tribufu API
 *
 * Use this class to interact with the Tribufu API.
 */
public class TribufuApi extends TribufuGeneratedApi {
    /**
     * The default base URL for the Tribufu API.
     */
    public static final String DEFAULT_BASE_URL = "https://api.tribufu.com";

    /**
     * The default URL of Tribufu Auth, which issues the access tokens.
     */
    public static final String DEFAULT_AUTH_URL = "https://auth.tribufu.com";

    private static final long TOKEN_REQUEST_TIMEOUT = 10_000;
    private static final long MAX_TOKEN_REFRESH_MARGIN = 30_000;

    private final Credentials credentials;
    private final OkHttpClient tokenClient;
    private String authUrl = getAuthUrl();
    private String accessToken;
    private long refreshAt;

    /**
     * Create a TribufuApi with no credentials (public access).
     */
    public TribufuApi() {
        this(null);
    }

    /**
     * Create a TribufuApi with the given credentials.
     *
     * @param credentials The credentials for authentication, or null for public access.
     */
    public TribufuApi(Credentials credentials) {
        super(new ApiClient());
        this.credentials = credentials;

        ApiClient client = getApiClient();
        client.setBasePath(getBaseUrl());
        client.setUserAgent(getUserAgent());

        OkHttpClient httpClient = client.getHttpClient();
        tokenClient = httpClient.newBuilder()
                .callTimeout(TOKEN_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .followRedirects(false)
                .build();

        if (credentials == null) {
            return;
        }

        switch (credentials.getType()) {
            case API_KEY:
                if (credentials.getApiKey() != null && !credentials.getApiKey().isEmpty()) {
                    client.addDefaultHeader("Authorization", "Bearer " + credentials.getApiKey());
                }
                break;
            case BEARER_TOKEN:
                if (credentials.getToken() != null && !credentials.getToken().isEmpty()) {
                    client.addDefaultHeader("Authorization", "Bearer " + credentials.getToken());
                }
                break;
            case CLIENT_CREDENTIALS:
                if (credentials.getClientId() == null || credentials.getClientId().isEmpty()
                        || credentials.getClientSecret() == null || credentials.getClientSecret().isEmpty()) {
                    throw new IllegalArgumentException("Client credentials are required");
                }

                client.setHttpClient(httpClient.newBuilder().addInterceptor(this::authorize).build());
                break;
        }
    }

    /**
     * Create a default TribufuApi instance.
     *
     * @return TribufuApi instance with default config.
     */
    public static TribufuApi defaultInstance() {
        return new TribufuApi();
    }

    public static TribufuApi withApiKey(String apiKey) {
        return new TribufuApi(Credentials.apiKey(apiKey));
    }

    public static TribufuApi withBearerToken(String token) {
        return new TribufuApi(Credentials.bearerToken(token));
    }

    public static TribufuApi withClientCredentials(String clientId, String clientSecret) {
        return new TribufuApi(Credentials.clientCredentials(clientId, clientSecret));
    }

    /**
     * Create a TribufuApi from environment variables: the client credentials of TRIBUFU_CLIENT_ID and
     * TRIBUFU_CLIENT_SECRET, or else the API key of TRIBUFU_API_KEY.
     *
     * @param prefix Optional prefix for env vars (default: TRIBUFU).
     * @return TribufuApi instance or null.
     */
    public static TribufuApi fromEnv(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            prefix = "TRIBUFU";
        }

        String clientId = System.getenv(prefix + "_CLIENT_ID");
        String clientSecret = System.getenv(prefix + "_CLIENT_SECRET");

        if (clientId != null || clientSecret != null) {
            return withClientCredentials(clientId != null ? clientId : "", clientSecret != null ? clientSecret : "");
        }

        String apiKey = System.getenv(prefix + "_API_KEY");

        if (apiKey != null && !apiKey.isEmpty()) {
            return withApiKey(apiKey);
        }

        return null;
    }

    /**
     * Create a TribufuApi from env vars or fallback to default instance.
     *
     * @param prefix Optional env var prefix (default: TRIBUFU).
     * @return TribufuApi instance.
     */
    public static TribufuApi fromEnvOrDefault(String prefix) {
        TribufuApi fromEnv = fromEnv(prefix);
        return (fromEnv != null) ? fromEnv : defaultInstance();
    }

    /**
     * Create a TribufuApi from env vars or fallback to default instance.
     *
     * @return TribufuApi instance.
     */
    public static TribufuApi fromEnvOrDefault() {
        return fromEnvOrDefault("TRIBUFU");
    }

    /**
     * Change the URL of Tribufu Auth, where the client credentials get the access tokens.
     */
    public void setAuthUrl(String authUrl) {
        synchronized (this) {
            this.authUrl = authUrl;
            clearAccessToken();
        }
    }

    private Response authorize(Interceptor.Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .header("Authorization", "Bearer " + getAccessToken())
                .build();

        Response response = chain.proceed(request);

        if (response.code() == 401) {
            synchronized (this) {
                clearAccessToken();
            }
        }

        return response;
    }

    private synchronized String getAccessToken() throws IOException {
        if (accessToken != null && System.currentTimeMillis() < refreshAt) {
            return accessToken;
        }

        return requestAccessToken();
    }

    // Keycloak issues the client token, to the service account of the client.
    private String requestAccessToken() throws IOException {
        String basic = urlEncode(credentials.getClientId()) + ":" + urlEncode(credentials.getClientSecret());
        long requestedAt = System.currentTimeMillis();

        Request request = new Request.Builder()
                .url(authUrl + "/realms/tribufu/protocol/openid-connect/token")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(basic.getBytes(StandardCharsets.UTF_8)))
                .header("Accept", "application/json")
                .post(new FormBody.Builder().add("grant_type", "client_credentials").build())
                .build();

        JsonObject body;

        try (Response response = tokenClient.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new IOException("Client token request failed (" + response.code() + ")");
            }

            JsonElement json = JsonParser.parseString(response.body().string());
            body = json.isJsonObject() ? json.getAsJsonObject() : new JsonObject();
        }

        JsonElement token = body.get("access_token");
        JsonElement tokenType = body.get("token_type");
        JsonElement expiresIn = body.get("expires_in");

        if (token == null || !token.isJsonPrimitive() || token.getAsString().isEmpty()
                || tokenType == null || !tokenType.isJsonPrimitive() || !"bearer".equalsIgnoreCase(tokenType.getAsString())
                || expiresIn == null || !expiresIn.isJsonPrimitive() || !expiresIn.getAsJsonPrimitive().isNumber()
                || expiresIn.getAsDouble() <= 0) {
            throw new IOException("Invalid client token response");
        }

        long lifetime = (long) (expiresIn.getAsDouble() * 1000);
        accessToken = token.getAsString();
        refreshAt = requestedAt + lifetime - Math.min(MAX_TOKEN_REFRESH_MARGIN, lifetime / 10);

        return accessToken;
    }

    private void clearAccessToken() {
        accessToken = null;
        refreshAt = 0;
    }

    /**
     * Get the base URL of the API, from TRIBUFU_API_URL or the default.
     */
    public static String getBaseUrl() {
        String baseUrl = System.getenv("TRIBUFU_API_URL");
        return baseUrl != null && !baseUrl.isEmpty() ? baseUrl : DEFAULT_BASE_URL;
    }

    /**
     * Get the URL of Tribufu Auth, from TRIBUFU_AUTH_URL or the default.
     */
    public static String getAuthUrl() {
        String authUrl = System.getenv("TRIBUFU_AUTH_URL");
        return authUrl != null && !authUrl.isEmpty() ? authUrl : DEFAULT_AUTH_URL;
    }

    /**
     * Get the version of the Tribufu client.
     */
    public static String getVersion() {
        Package pkg = TribufuApi.class.getPackage();
        String version = Optional.ofNullable(pkg.getImplementationVersion()).orElse("dev");
        return version;
    }

    /**
     * Get the User-Agent string for the API.
     */
    public static String getUserAgent() {
        String version = getVersion();
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String javaVersion = System.getProperty("java.version");
        return String.format("Tribufu/%s (Java %s; %s; %s)", version, javaVersion, os, arch);
    }

    private static String urlEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }
}
