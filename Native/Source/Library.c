// Copyright (c) TribuFu. All Rights Reserved

#include <jni.h>
#include "TribuFu.h"
#include "Library.h"

JNIEXPORT jstring JNICALL Java_tribufu_TribuFu_Hello(JNIEnv *env, jobject obj, jstring str)
{
    char *result = Hello("C");

    return result;
}
