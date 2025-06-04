// Copyright (c) Tribufu. All Rights Reserved.
// SPDX-License-Identifier: MIT

package com.tribufu.example;

import com.tribufu.TribufuApi;

public class Main {
    public static void main(String[] args) {
        String userAgent = TribufuApi.getUserAgent();
        System.out.println(userAgent);
    }
}
