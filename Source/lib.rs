// Copyright (c) TribuFu. All Rights Reserved

//! Jvm bindings to TribuFu SDK.

#![allow(non_snake_case)]
#![allow(unused_variables)]
#![allow(unused_imports)]

#[cfg(not(any(
    target_os = "windows",
    target_os = "macos",
    target_os = "linux",
    target_os = "android",
)))]
compile_error!("INVALID_TARGET_PLATFORM");

use jni::objects::{JClass, JString};
use jni::sys::jstring;
use jni::JNIEnv;
use std::ffi::{CStr, CString};

#[no_mangle]
// This is the class that owns our static method. It's not going to be used,
// but still must be present to match the expected signature of a static
// native method.
pub extern "system" fn Java_com_tribufu_sdk_TribuFu_Hello(
    env: JNIEnv,
    _: JClass,
    j_input: JString,
) -> jstring {
    // First, we have to get the string out of Java. Check out the `strings`
    // module for more info on how this works.
    let input = CString::from(unsafe { CStr::from_ptr(env.get_string(j_input).unwrap().as_ptr()) });

    // Then we have to create a new Java string to return. Again, more info
    // in the `strings` module.
    let res = env.new_string(input.to_str().unwrap()).unwrap();

    // Finally, extract the raw pointer to return.
    return res.into_inner();
}
