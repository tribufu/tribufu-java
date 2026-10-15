// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu;

/**
 * How {@link TribufuApi} authenticates its requests.
 */
public final class Credentials {
    private final CredentialsType type;
    private final String apiKey;
    private final String token;
    private final String clientId;
    private final String clientSecret;

    private Credentials(CredentialsType type, String apiKey, String token, String clientId, String clientSecret) {
        this.type = type;
        this.apiKey = apiKey;
        this.token = token;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static Credentials apiKey(String apiKey) {
        return new Credentials(CredentialsType.API_KEY, apiKey, null, null, null);
    }

    public static Credentials bearerToken(String token) {
        return new Credentials(CredentialsType.BEARER_TOKEN, null, token, null, null);
    }

    /**
     * Credentials of a client, whose service account gets the access tokens.
     */
    public static Credentials clientCredentials(String clientId, String clientSecret) {
        return new Credentials(CredentialsType.CLIENT_CREDENTIALS, null, null, clientId, clientSecret);
    }

    public CredentialsType getType() {
        return type;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getToken() {
        return token;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
