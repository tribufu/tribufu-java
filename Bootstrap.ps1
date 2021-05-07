#!/usr/bin/env pwsh

# Windows

if ($IsWindows)
{
}

# Mac

elseif ($IsMacOS)
{
    sudo chmod +x ./gradlew
    sudo chmod +x ./Scripts/Package.ps1
}

# Linux

elseif ($IsLinux)
{
    sudo chmod +x ./gradlew
    sudo chmod +x ./Scripts/Package.ps1
}
