#!/usr/bin/env pwsh

./gradlew build
./gradlew jar

New-Item -Path "./Release" -ItemType Directory -Force

Copy-Item -Path "./lib/build/libs/lib-0.0.1.jar" -Destination "./Release/TribuFu-0.0.1.jar" -Recurse -Force
