// Copyright (c) TribuFu. All Rights Reserved

package tribufu

class TribuFu {
    /**
     * A native method that is implemented by the 'TribuFu' native library.
     */
    // external fun Hello(): Int

    // Used to load the 'TribuFu' library on application startup.
    fun LoadLibrary() {
        System.loadLibrary("TribuFu")
    }
}
