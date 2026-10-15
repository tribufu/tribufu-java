// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu;

/**
 * Tribufu API
 *
 * Creates the clients of the Tribufu API, each one with its own credentials.
 */
public final class TribufuApiFactory {
    private TribufuApiFactory() {
    }

    /**
     * Client without credentials, for the endpoints that do not need authentication.
     */
    public static TribufuApi anonymous() {
        return TribufuApi.defaultInstance();
    }

    /**
     * Client with the credentials of the environment variables, or without credentials when they are not set.
     */
    public static TribufuApi fromEnv() {
        return TribufuApi.fromEnvOrDefault();
    }

    /**
     * Client with the credentials of the environment variables, or without credentials when they are not set.
     *
     * @param prefix Prefix of the environment variables (default: TRIBUFU).
     */
    public static TribufuApi fromEnv(String prefix) {
        return TribufuApi.fromEnvOrDefault(prefix);
    }

    /**
     * Client acting for the owner of the API key.
     */
    public static TribufuApi forApiKey(String apiKey) {
        return TribufuApi.withApiKey(apiKey);
    }

    /**
     * Client acting for a signed in user or client, with its access token.
     */
    public static TribufuApi forAccessToken(String accessToken) {
        return TribufuApi.withBearerToken(accessToken);
    }

    /**
     * Client acting for a client, with its client credentials.
     *
     * The client keeps its access token, so reuse it instead of creating one for each request.
     */
    public static TribufuApi forClientCredentials(String clientId, String clientSecret) {
        return TribufuApi.withClientCredentials(clientId, clientSecret);
    }
}
