// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu;

/**
 * Tribufu Bot
 *
 * To authenticate a bot you need to use the bot token obtained from the Tribufu
 * Developer Portal.
 *
 * 1. A bot is a special type of user account.
 * 2. A bot give you read and write access to the Tribufu API.
 */
public class TribufuBot extends TribufuApi {
    private final String botId;

    /**
     * Create a TribufuBot.
     *
     * @param botId
     * @param botToken
     */
    public TribufuBot(String botId, String botToken) {
        super(new TribufuApiOptions(botToken, null, 0));
        this.botId = botId;
    }

    /**
     * Try to create a TribufuBot from environment variables.
     *
     * This will only work if the environment variables are set.
     */
    public static TribufuBot fromEnv() {
        return TribufuBot.fromEnv(null);
    }

    /**
     * Try to create a TribufuBot from environment variables.
     *
     * This will only work if the environment variables are set.
     *
     * @param prefix A prefix for the environment variables.
     */
    public static TribufuBot fromEnv(String prefix) {
        var envPrefix = "";

        if (prefix != null && !prefix.isEmpty()) {
            envPrefix = prefix + "_";
        }

        var botId = System.getenv(envPrefix + "BOT_ID");
        var botToken = System.getenv(envPrefix + "BOT_TOKEN");

        if (botId != null && botToken != null) {
            return new TribufuBot(botId, botToken);
        }

        return null;
    }

    /**
     * Get the bot id.
     */
    public String getBotId() {
        return this.botId;
    }
}
