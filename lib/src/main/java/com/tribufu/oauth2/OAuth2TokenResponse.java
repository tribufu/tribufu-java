// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.oauth2;

public class OAuth2TokenResponse {
    private final OAuth2TokenType tokenType;
    private final String accessToken;
    private final String refreshToken;
    private final String scope;
    private final int expiresIn;

    public OAuth2TokenResponse(OAuth2TokenType tokenType, String accessToken, String refreshToken, String scope,
            int expiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.expiresIn = expiresIn;
    }
}
