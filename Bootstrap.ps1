#!/usr/bin/env pwsh

if ($IsWindows)
{
}
else
{
    sudo chmod +x ./gradlew
    sudo chmod +x ./Scripts/Package.ps1
}
