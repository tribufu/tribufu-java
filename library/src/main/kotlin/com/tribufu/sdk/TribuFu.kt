// Copyright (c) TribuFu. All Rights Reserved

package com.tribufu.sdk

class TribuFu {
    // Used to load the 'TribuFu' library on application startup.
    init {
        System.loadLibrary("TribuFu_Jvm")
    }

    /** A native method that is implemented by the 'TribuFu' native library. */
    external fun getVersion(): String
}
