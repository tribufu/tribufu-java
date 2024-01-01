// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu;

/**
 * Tribufu Server
 *
 * To authenticate a server you need to use the server id, client id and client
 * secret obtained from your server subscription.
 *
 * 1. A server is a special type of client application.
 * 2. A server is how game servers interact with the Tribufu API.
 * 3. A server give you read and write access to the Tribufu API.
 */
public class TribufuServer extends TribufuClient {
    private final String serverId;

    /**
     * Create a TribufuServer.
     *
     * @param serverId
     * @param clientId
     * @param clientSecret
     */
    public TribufuServer(String serverId, String clientId, String clientSecret) {
        super(clientId, clientSecret);
        this.serverId = serverId;
    }

    /**
     * Try to create a TribufuServer from environment variables.
     *
     * This will only work if the environment variables are set.
     */
    public static TribufuServer fromEnv() {
        return TribufuServer.fromEnv(null);
    }

    /**
     * Try to create a TribufuServer from environment variables.
     *
     * This will only work if the environment variables are set.
     *
     * @param prefix A prefix for the environment variables.
     */
    public static TribufuServer fromEnv(String prefix) {
        var envPrefix = "";

        if (prefix != null && !prefix.isEmpty()) {
            envPrefix = prefix + "_";
        }

        var serverId = System.getenv(envPrefix + "SERVER_ID");
        var clientId = System.getenv(envPrefix + "CLIENT_ID");
        var clientSecret = System.getenv(envPrefix + "CLIENT_SECRET");

        if (serverId != null && clientId != null && clientSecret != null) {
            return new TribufuServer(serverId, clientId, clientSecret);
        }

        return null;
    }

    /**
     * Get the server id.
     */
    public String getServerId() {
        return this.serverId;
    }
}
