#!/usr/bin/env pwsh

./gradlew build
./gradlew jar

New-Item -Path "./Release" -ItemType Directory -Force
New-Item -Path "./Release/Windows" -ItemType Directory -Force
New-Item -Path "./Release/Mac" -ItemType Directory -Force
New-Item -Path "./Release/Linux" -ItemType Directory -Force
New-Item -Path "./Release/Android" -ItemType Directory -Force

Copy-Item -Path "./lib/build/libs/lib-0.0.1.jar" -Destination "./Release/TribuFu.jar" -Recurse -Force

Copy-Item -Path "./Binaries/debug/TribuFu_jvm.dll" -Destination "./Release/Windows/TribuFu_jvm.dll" -Recurse -Force
