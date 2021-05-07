// Copyright (c) TribuFu. All Rights Reserved

package tribufu;

public class TribuFu {
    /**
     * A native method that is implemented by the 'TribuFu' native library.
     */
    public native String Hello(String input);

    // Used to load the 'TribuFu' library on application startup.
    public void LoadLibrary() {
        System.loadLibrary("TribuFu");
    }
}
