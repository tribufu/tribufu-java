#!/usr/bin/env pwsh

echo "Building Native Library"

cargo build --release

echo "Building JVM Library"

./gradlew :library:build
./gradlew :library:jar

New-Item -Path "./Release" -ItemType Directory -Force
New-Item -Path "./Release/Windows" -ItemType Directory -Force
New-Item -Path "./Release/Mac" -ItemType Directory -Force
New-Item -Path "./Release/Linux" -ItemType Directory -Force

Copy-Item -Path "./library/build/libs/library-0.0.1.jar" -Destination "./Release/TribuFu.jar" -Recurse -Force
Copy-Item -Path "./Binaries/release/TribuFu_Jvm.dll" -Destination "./Release/Windows/TribuFu_Jvm.dll" -Recurse -Force

echo "Building Android Library"

cargo ndk -t armeabi-v7a -t arm64-v8a -t x86 -t x86_64 build --release

New-Item -Path "./android/src/main/jniLibs" -ItemType Directory -Force
New-Item -Path "./android/src/main/jniLibs/arm64-v8a" -ItemType Directory -Force
New-Item -Path "./android/src/main/jniLibs/armeabi-v7a" -ItemType Directory -Force
New-Item -Path "./android/src/main/jniLibs/x86_64" -ItemType Directory -Force
New-Item -Path "./android/src/main/jniLibs/x86" -ItemType Directory -Force

Copy-Item -Path "./Binaries/aarch64-linux-android/release/libTribuFu_Jvm.so" -Destination "./android/src/main/jniLibs/arm64-v8a/libTribuFu_Jvm.so" -Recurse -Force
Copy-Item -Path "./Binaries/armv7-linux-androideabi/release/libTribuFu_Jvm.so" -Destination "./android/src/main/jniLibs/armeabi-v7a/libTribuFu_Jvm.so"  -Recurse -Force
Copy-Item -Path "./Binaries/x86_64-linux-android/release/libTribuFu_Jvm.so" -Destination "./android/src/main/jniLibs/x86_64/libTribuFu_Jvm.so" -Recurse -Force
Copy-Item -Path "./Binaries/i686-linux-android/release/libTribuFu_Jvm.so" -Destination "./android/src/main/jniLibs/x86/libTribuFu_Jvm.so" -Recurse -Force

New-Item -Path "./android/libs" -ItemType Directory -Force
Copy-Item -Path "./Release/TribuFu.jar" -Destination "./android/libs/TribuFu.jar" -Recurse -Force

./gradlew :android:build

Copy-Item -Path "./android/build/outputs/aar/android-release.aar" -Destination "./Release/TribuFu.aar" -Recurse -Force
