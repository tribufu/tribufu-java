// Copyright (c) TribuFu. All Rights Reserved

package com.tribufu.sdk

class TribuFu {
    Init()

    // Used to load the 'TribuFu' library on application startup.
    private def Init() {
        System.loadLibrary("TribuFu_jvm")
    }

    /**
     * A native method that is implemented by the 'TribuFu' native library.
     */
    @native def Hello(input: String): String
}
