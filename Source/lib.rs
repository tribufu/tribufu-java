// Copyright (c) TribuFu. All Rights Reserved.

//! JNI bindings to TribuFu SDK.

#![allow(non_snake_case)]

use jni::objects::{JClass, JString};
use jni::sys::jstring;
use jni::JNIEnv;
use std::ffi::{CStr, CString};

#[no_mangle]
pub extern "system" fn Java_com_tribufu_sdk_TribuFu_getVersion(
    env: JNIEnv,
    _class: JClass,
    input: JString,
) -> jstring {
    // First, we have to get the string out of Java. Check out the `strings`
    // module for more info on how this works.
    let input = CString::from(unsafe { CStr::from_ptr(env.get_string(input).unwrap().as_ptr()) });

    // Then we have to create a new Java string to return. Again, more info
    // in the `strings` module.
    let output = env.new_string(input.to_str().unwrap()).unwrap();

    // Finally, extract the raw pointer to return.
    return output.into_inner();
}
