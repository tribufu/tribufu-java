#!/usr/bin/env pwsh

echo "Building Native Library"

cargo build --release

echo "Building JVM Library"

./gradlew build
./gradlew jar

New-Item -Path "./Release" -ItemType Directory -Force

New-Item -Path "./Release/Windows" -ItemType Directory -Force
New-Item -Path "./Release/Mac" -ItemType Directory -Force
New-Item -Path "./Release/Linux" -ItemType Directory -Force
New-Item -Path "./Release/Android" -ItemType Directory -Force

New-Item -Path "./Release/Android/arm64-v8a" -ItemType Directory -Force
New-Item -Path "./Release/Android/armeabi-v7a" -ItemType Directory -Force
New-Item -Path "./Release/Android/x86" -ItemType Directory -Force
New-Item -Path "./Release/Android/x86_64" -ItemType Directory -Force

echo "Copying Artifacts"

Copy-Item -Path "./lib/build/libs/lib-0.0.1.jar" -Destination "./Release/TribuFu.jar" -Recurse -Force
Copy-Item -Path "./Binaries/release/TribuFu_jvm.dll" -Destination "./Release/Windows/TribuFu_jvm.dll" -Recurse -Force
