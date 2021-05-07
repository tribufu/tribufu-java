// Copyright (c) TribuFu. All Rights Reserved

#pragma once

#include <jni.h>
#include "TribuFu.h"

/*
 * Class:     tribufu_TribuFu
 * Method:    Hello
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_tribufu_TribuFu_Hello(JNIEnv *, jobject, jstring);
