// Copyright (c) TribuFu. All Rights Reserved

package tribufu

class TribuFu {
    /**
     * A native method that is implemented by the 'TribuFu' native library, which is packaged with
     * this library.
     */
    // external fun Hello(): Int

    companion object {
        // Used to load the 'TribuFu' library on application startup.
        init {
            //System.loadLibrary("TribuFu")
        }
    }
}
