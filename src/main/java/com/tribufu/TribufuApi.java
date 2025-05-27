// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu;

import com.tribufu.generated.api.TribufuGeneratedApi;
import com.tribufu.generated.ApiClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

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
     * Create a TribufuApi with no API key (public access).
     */
    public TribufuApi() {
        this("");
    }

    /**
     * Create a TribufuApi with the given API key.
     *
     * @param apiKey The API key for authentication.
     */
    public TribufuApi(String apiKey) {
        super(createClient(apiKey));
    }

    /**
     * Create a default TribufuApi instance.
     *
     * @return TribufuApi instance with default config.
     */
    public static TribufuApi defaultInstance() {
        return new TribufuApi();
    }

    /**
     * Create a TribufuApi with the given API key.
     *
     * @param apiKey The API key.
     * @return TribufuApi instance.
     */
    public static TribufuApi withApiKey(String apiKey) {
        return new TribufuApi(apiKey);
    }

    /**
     * Create a TribufuApi from environment variables (e.g., TRIBUFU_API_KEY).
     *
     * @param prefix Optional prefix for env vars (default: TRIBUFU).
     * @return TribufuApi instance or null.
     */
    public static TribufuApi fromEnv(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            prefix = "TRIBUFU";
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
     * Creates and configures the ApiClient.
     */
    private static ApiClient createClient(String apiKey) {
        ApiClient client = new ApiClient();

        client.setBasePath(getBaseUrl());

        try {
            client.setUserAgent(urlEncode(getUserAgent()));
        } catch (UnsupportedEncodingException e) {
        }

        if (apiKey != null && !apiKey.isEmpty()) {
            client.addDefaultHeader("Authorization", "ApiKey " + apiKey);
        }

        return client;
    }

    /**
     * Get the base URL, uses TRIBUFU_API_URL if debug mode is on.
     */
    private static String getBaseUrl() {
        String debug = System.getenv("DEBUG");
        String envUrl = System.getenv("TRIBUFU_API_URL");

        if ("1".equals(debug) || "true".equalsIgnoreCase(debug)) {
            if (envUrl != null && !envUrl.isEmpty()) {
                return envUrl;
            }
        }

        return DEFAULT_BASE_URL;
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
    private static String getUserAgent() {
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
