#!/usr/bin/env pwsh

.\gradlew build

New-Item "build/libs" -ItemType Directory -Force
Remove-Item -Path "build/libs/*" -Force
Copy-Item -Path "./lib/build/libs/*.jar" -Destination "./build/libs" -Force
Copy-Item -Path "./vendor/MintakaJvm/lib/build/libs/*.jar" -Destination "./build/libs" -Force
