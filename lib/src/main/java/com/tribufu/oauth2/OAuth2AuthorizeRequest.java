// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.oauth2;

public class OAuth2AuthorizeRequest {
    private final OAuth2ResponseType responseType;
    private final String clientId;
    private final String scope;
    private final String redirectUri;
    private final String state;

    public OAuth2AuthorizeRequest(OAuth2ResponseType responseType, String clientId, String scope, String redirectUri,
            String state) {
        this.responseType = responseType;
        this.clientId = clientId;
        this.scope = scope;
        this.redirectUri = redirectUri;
        this.state = state;
    }
}
