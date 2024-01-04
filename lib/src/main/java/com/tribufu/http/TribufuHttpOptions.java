// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.http;

import java.util.HashMap;
import java.util.Map;

public class TribufuHttpOptions {
    public String baseUrl;
    public Map<String, String> headers;
    public boolean logEnabled;
    public String logTarget;

    public TribufuHttpOptions() {
        this(null);
    }

    public TribufuHttpOptions(String baseUrl) {
        this(baseUrl, new HashMap<>(), false, "TribufuHttp");
    }

    public TribufuHttpOptions(String baseUrl, Map<String, String> headers, boolean logEnabled, String logTarget) {
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.logEnabled = logEnabled;
        this.logTarget = logTarget;
    }
}
