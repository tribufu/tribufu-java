// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu;

import com.tribufu.types.games.Game;
import com.tribufu.types.servers.Server;
import com.tribufu.types.users.Profile;
import com.tribufu.http.HttpClient;
import java.util.concurrent.CompletableFuture;
import java.util.HashMap;
import java.util.List;
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
    private static final String API_URL = "http://localhost:5000";

    protected final String baseUrl;
    protected final TribufuApiOptions options;
    protected final HttpClient http;

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
        this.http = new HttpClient(baseUrl, TribufuApi.defaultHeaders(), true, TribufuApi.API_URL);
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

    /**
     * Get games from the Tribufu API.
     *
     * @param page
     */
    public List<Game> getGames() {
        Map<String, String> headers = this.getHeaders();
        return this.http.getArray("/v1/packages", headers, Game[].class);
    }

    /**
     * Get a game from the Tribufu API.
     *
     * @param id
     */
    public CompletableFuture<Game> getGameById(String id) {
        Map<String, String> headers = this.getHeaders();
        return this.http.get("/v1/packages/" + id, headers, Game.class);
    }

    /**
     * Get a game from the Tribufu API.
     *
     * @param page
     */
    public List<Server> getServers() {
        Map<String, String> headers = this.getHeaders();
        return this.http.getArray("/v1/servers", headers, Server[].class);
    }

    /**
     * Get a server by id from the Tribufu API.
     *
     * @param id
     */
    public CompletableFuture<Server> getServerById(String id) {
        Map<String, String> headers = this.getHeaders();
        return this.http.get("/v1/servers/" + id, headers, Server.class);
    }

    /**
     * Get a server by address from the Tribufu API.
     *
     * @param id
     */
    public CompletableFuture<Server> getServerByAddress(String address) {
        Map<String, String> headers = this.getHeaders();
        return this.http.get("/v1/servers/address/" + address, headers, Server.class);
    }

    /**
     * Get a user by id from the Tribufu API.
     *
     * @param id
     */
    public CompletableFuture<Profile> getUsersById(String id) {
        Map<String, String> headers = this.getHeaders();
        return this.http.get("/v1/users/" + id, headers, Profile.class);
    }

    /**
     * Get a user by name from the Tribufu API.
     *
     * @param uuid
     */
    public CompletableFuture<Profile> getUsersByUuid(String uuid) {
        return this.getUserByKey("uuid", uuid);
    }

    /**
     * Get a user by email from the Tribufu API.
     *
     * @param email
     */
    public CompletableFuture<Profile> getUsersByName(String email) {
        return this.getUserByKey("email", email);
    }

    /**
     * Get a user by custom key from the Tribufu API.
     *
     * @param key
     * @param value
     */
    private CompletableFuture<Profile> getUserByKey(String key, String value) {
        Map<String, String> headers = this.getHeaders();
        return this.http.get("/v1/users?" + key + "=" + value, headers, Profile.class);
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
