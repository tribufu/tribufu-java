// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu;

import com.tribufu.http.TribufuHttp;
import java.util.HashMap;
import java.util.Map;

/**
 * Tribufu API
 *
 * Use this class to interact with the Tribufu API.
 *
 * There are three ways to use the Tribufu API:
 * 1. A api key give you public read only access to the Tribufu API.
 * 2. A bot give you read and write access to the Tribufu API as a bot account.
 * 3. A client give you read and write access to the Tribufu API as a client
 * application.
 */
public class TribufuApi {
    private static final String VERSION = "0.0.0";
    private static final String API_URL = "https://api.tribufu.com";

    protected final String baseUrl;
    protected final TribufuApiOptions options;
    protected final TribufuHttp http;

    /**
     * Create a TribufuApi with the default options.
     */
    public TribufuApi() {
        this(new TribufuApiOptions());
    }

    /**
     * Create a TribufuApi with the given api key.
     *
     * A api key give you public read only access to the Tribufu API.
     *
     * @param apiKey
     */
    public TribufuApi(String apiKey) {
        this(new TribufuApiOptions(apiKey));
    }

    /**
     * Create a TribufuApi with the given options.
     *
     * @param options
     */
    public TribufuApi(TribufuApiOptions options) {
        this.options = options;
        this.baseUrl = TribufuApi.getBaseUrl();
        this.http = new TribufuHttp(baseUrl, TribufuApi.defaultHeaders(), true, TribufuApi.API_URL);
    }

    /**
     * Try to create a TribufuApi from environment variables.
     *
     * This will only work if the environment variables are set.
     */
    public static TribufuApi fromEnv() {
        return TribufuApi.fromEnv(null);
    }

    /**
     * Try to create a TribufuApi from environment variables.
     *
     * This will only work if the environment variables are set.
     *
     * @param prefix A prefix for the environment variables.
     */
    public static TribufuApi fromEnv(String prefix) {
        var envPrefix = "";

        if (prefix != null && !prefix.isEmpty()) {
            envPrefix = prefix + "_";
        }

        var apiKey = System.getenv(envPrefix + "API_KEY");

        if (apiKey != null && !apiKey.isEmpty()) {
            return new TribufuApi(apiKey);
        }

        return null;
    }

    /**
     * Create a TribufuApi from environment variables or the default api.
     *
     * This will fallback to the default api if the environment variables are not
     * set.
     */
    public static TribufuApi fromEnvOrDefault() {
        return TribufuApi.fromEnvOrDefault(null);
    }

    /**
     * Create a TribufuApi from environment variables or the default api.
     *
     * This will fallback to the default api if the environment variables are not
     * set.
     *
     * @param prefix A prefix for the environment variables.
     */
    public static TribufuApi fromEnvOrDefault(String prefix) {
        var api = TribufuApi.fromEnv(prefix);

        if (api != null) {
            return api;
        }

        return new TribufuApi();
    }

    /**
     * Get the base url for the Tribufu API.
     *
     * The base url can be set using the environment variable TRIBUFU_API_URL.
     * The custom base url is only used if debug mode is enabled.
     * The default base url is https://api.tribufu.com.
     */
    private static String getBaseUrl() {
        var baseUrl = System.getenv("TRIBUFU_API_URL");

        if (baseUrl != null && !baseUrl.isEmpty()) {
            return baseUrl;
        }

        return TribufuApi.API_URL;
    }

    /**
     * Get the default headers for the Tribufu API.
     */
    public static Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Tribufu-Language", "java");
        headers.put("X-Tribufu-Version", VERSION);
        return headers;
    }

    /**
     * Get current headers with the api key or access token.
     */
    protected Map<String, String> getHeaders() {
        Map<String, String> headers = TribufuApi.defaultHeaders();

        if (this.options.apiKey != null && !this.options.apiKey.isEmpty()) {
            headers.put("Authorization", "ApiKey " + this.options.apiKey);
            return headers;
        }

        if (this.options.accessToken != null && !this.options.accessToken.isEmpty()) {
            headers.put("Authorization", "Bearer " + this.options.accessToken);
            return headers;
        }

        return headers;
    }

    /*
     * private OAuth2TokenResponse getOAuthToken(OAuth2GrantType grantType, String
     * grantValue, String clientId,
     * String clientSecret, String subjectKey, String subjectValue) {
     * try {
     * var headers = defaultHeaders();
     * headers.put("Content-Type", "application/x-www-form-urlencoded");
     *
     * var requestBuilder = HttpRequest.newBuilder();
     * for (var entry : headers.entrySet()) {
     * requestBuilder.header(entry.getKey(), entry.getValue());
     * }
     *
     * var request = requestBuilder.build();
     * var response = this.http.send(request, HttpResponse.BodyHandlers.ofString());
     *
     * if (response.statusCode() != 200) {
     * return null;
     * }
     *
     * return null;
     * } catch (Exception e) {
     * return null;
     * }
     * }
     */
}
