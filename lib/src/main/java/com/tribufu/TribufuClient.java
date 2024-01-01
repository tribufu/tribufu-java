// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu;

import java.util.Base64;
import java.util.Map;

/**
 * Tribufu Client
 *
 * To authenticate a client you need to use the client id and client secret
 * obtained from the Tribufu Developer Portal
 *
 * 1. A client is how external applications interact with the Tribufu API.
 * 2. A client give you read and write access to the Tribufu API.
 * 3. A client can be used to login users.
 */
public class TribufuClient extends TribufuApi {
    private final String clientId;
    private final String clientSecret;

    /**
     * Create a TribufuClient.
     *
     * @param clientId
     * @param clientSecret
     */
    public TribufuClient(String clientId, String clientSecret) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Try to create a TribufuClient from environment variables.
     *
     * This will only work if the environment variables are set.
     */
    public static TribufuClient fromEnv() {
        return TribufuClient.fromEnv(null);
    }

    /**
     * Try to create a TribufuClient from environment variables.
     *
     * This will only work if the environment variables are set.
     *
     * @param prefix A prefix for the environment variables.
     */
    public static TribufuClient fromEnv(String prefix) {
        var envPrefix = "";
        if (prefix != null && !prefix.isEmpty()) {
            envPrefix = prefix + "_";
        }

        var clientId = System.getenv(envPrefix + "CLIENT_ID");
        var clientSecret = System.getenv(envPrefix + "CLIENT_SECRET");

        if (clientId != null && clientSecret != null) {
            return new TribufuClient(clientId, clientSecret);
        }

        return null;
    }

    /**
     * Set the tokens.
     *
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     */
    private void setTokens(String accessToken, String refreshToken, float expiresIn) {
        this.options.accessToken = accessToken;
        this.options.refreshToken = refreshToken;
        this.options.expiresIn = expiresIn;
    }

    /**
     * Clear the tokens.
     */
    protected void clearTokens() {
        this.setTokens(null, null, 0);
    }

    /**
     * Get the headers for a oauth2 request.
     */
    protected Map<String, String> getOAuthHeaders() {
        var headers = this.getHeaders();
        var credentials = this.clientId + ":" + this.clientSecret;
        var credentialsBase64 = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.put("Authorization", "Basic " + credentialsBase64);
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    /**
     * Get the client id.
     */
    public String getClientId() {
        return this.clientId;
    }
}
