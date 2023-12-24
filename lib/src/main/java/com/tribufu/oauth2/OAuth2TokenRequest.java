// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.oauth2;

public class OAuth2TokenRequest {
    private final OAuth2GrantType grantType;
    private final String code;
    private final String refreshToken;
    private final String username;
    private final String password;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    public OAuth2TokenRequest(OAuth2GrantType grantType, String code, String refreshToken, String username,
            String password, String clientId, String clientSecret, String redirectUri) {
        this.grantType = grantType;
        this.code = code;
        this.refreshToken = refreshToken;
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
