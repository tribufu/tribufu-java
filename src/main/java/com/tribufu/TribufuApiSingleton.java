// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu;

/**
 * Tribufu API
 *
 * Helper class to get a singleton instance of the Tribufu API.
 */
public final class TribufuApiSingleton {
    private static TribufuApi instance = null;

    private TribufuApiSingleton() {
    }

    /**
     * Get the singleton instance of {@link TribufuApi}.
     *
     * @return Singleton instance
     */
    public static synchronized TribufuApi getInstance() {
        if (instance == null) {
            instance = TribufuApi.fromEnvOrDefault();
        }

        return instance;
    }

    /**
     * Reset the singleton instance of {@link TribufuApi}.
     */
    public static synchronized void resetInstance() {
        instance = null;
    }
}
