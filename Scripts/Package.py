#!/usr/bin/env python3

import os
import subprocess
import platform
import shutil

from pathlib import Path

Path("./Release").mkdir(parents=True, exist_ok=True)

if os.path.exists("./Release/Windows"):
    shutil.rmtree("./Release/Windows")

Path("./Release/Windows").mkdir(parents=True, exist_ok=True)

if os.path.exists("./Release/Mac"):
    shutil.rmtree("./Release/Mac")

Path("./Release/Mac").mkdir(parents=True, exist_ok=True)

if os.path.exists("./Release/Linux"):
    shutil.rmtree("./Release/Linux")

Path("./Release/Linux").mkdir(parents=True, exist_ok=True)

print("Building Native Library")

os.system("cargo build --release")

# Windows

if platform.system() == "Windows":
    shutil.copy2("./Binaries/release/TribuFu_Jvm.dll", "./Release/Windows/TribuFu_Jvm.dll")

# Mac

elif platform.system() == "Mac":
    shutil.copy2("./Binaries/release/libTribuFu_Jvm.dylib", "./Release/Windows/libTribuFu_Jvm.dylib")

# Linux

elif platform.system() == "Linux":
    shutil.copy2("./Binaries/release/libTribuFu_Jvm.so", "./Release/Windows/libTribuFu_Jvm.so")

# Android

os.system("cargo ndk -t armeabi-v7a -t arm64-v8a -t x86 -t x86_64 build --release")

Path("./android/src/main/jniLibs").mkdir(parents=True, exist_ok=True)
Path("./android/src/main/jniLibs/arm64-v8a").mkdir(parents=True, exist_ok=True)
Path("./android/src/main/jniLibs/armeabi-v7a").mkdir(parents=True, exist_ok=True)
Path("./android/src/main/jniLibs/x86_64").mkdir(parents=True, exist_ok=True)
Path("./android/src/main/jniLibs/x86").mkdir(parents=True, exist_ok=True)

shutil.copy2("./Binaries/aarch64-linux-android/release/libTribuFu_Jvm.so", "./android/src/main/jniLibs/arm64-v8a/libTribuFu_Jvm.so")
shutil.copy2("./Binaries/armv7-linux-androideabi/release/libTribuFu_Jvm.so", "./android/src/main/jniLibs/armeabi-v7a/libTribuFu_Jvm.so" )
shutil.copy2("./Binaries/x86_64-linux-android/release/libTribuFu_Jvm.so", "./android/src/main/jniLibs/x86_64/libTribuFu_Jvm.so")
shutil.copy2("./Binaries/i686-linux-android/release/libTribuFu_Jvm.so", "./android/src/main/jniLibs/x86/libTribuFu_Jvm.so")

print("Building JVM Library")

if platform.system() == "Windows":
    subprocess.run("gradlew.bat :library:build")
    subprocess.run("gradlew.bat :library:jar")
else:
    subprocess.run("./gradlew :library:build")
    subprocess.run("./gradlew :library:jar")

shutil.copy2("./library/build/libs/library-0.0.1.jar", "./Release/TribuFu.jar")

print("Building Android Library")

Path("./android/libs").mkdir(parents=True, exist_ok=True)

shutil.copy2("./Release/TribuFu.jar", "./android/libs/TribuFu.jar")

if platform.system() == "Windows":
    subprocess.run("gradlew.bat :android:build")
else:
    subprocess.run("./gradlew :android:build")

shutil.copy2("./android/build/outputs/aar/android-release.aar", "./Release/TribuFu.aar")
