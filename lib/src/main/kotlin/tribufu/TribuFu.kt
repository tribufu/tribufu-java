// Copyright (c) TribuFu. All Rights Reserved

package tribufu

class TribuFu {
    /**
     * A native method that is implemented by the 'TribuFu' native library.
     */
    external fun Hello(input: String): String

    // Used to load the 'TribuFu' library on application startup.
    init {
        System.loadLibrary("TribuFu_jvm")
    }
}
