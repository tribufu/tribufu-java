// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.sdk;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

public class TribufuClient {
    public static final String VERSION = "0.0.0";

    private final long id;
    private final String secret;
    private final HttpClient http;
    private Map<String, String> defaultHeaders;

    public TribufuClient(long id, String secret) {
        this.id = id;
        this.secret = secret;

        String targetTriple = "Java";
        String userAgent = String.format("Tribufu/%s (+https://api.tribufu.com; %s)", VERSION, targetTriple);

        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", userAgent);
        headers.put("X-Tribufu-Language", "java");
        headers.put("X-Tribufu-Version", VERSION);

        this.defaultHeaders = headers;

        this.http = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public long getId() {
        return this.id;
    }
}
