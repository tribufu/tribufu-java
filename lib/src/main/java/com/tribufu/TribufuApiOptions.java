// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu;

public class TribufuApiOptions {
    public String apiKey = null;
    public String accessToken = null;
    public String refreshToken = null;
    public float expiresIn = 0;

    public TribufuApiOptions() {
        this(null, null, null, 0);
    }

    public TribufuApiOptions(String apiKey) {
        this(apiKey, null, null, 0);
    }

    public TribufuApiOptions(String accessToken, String refreshToken, float expiresIn) {
        this(null, accessToken, refreshToken, expiresIn);
    }

    private TribufuApiOptions(String apiKey, String accessToken, String refreshToken, float expiresIn) {
        this.apiKey = apiKey;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
}
