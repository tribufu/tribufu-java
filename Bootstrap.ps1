#!/usr/bin/env pwsh

# Windows

if ($IsWindows)
{
    echo "Generating Visual Studio project..."

    & "./Vendor/Premake/Windows/premake5.exe" "vs2019"
}

# Mac

elseif ($IsMacOS)
{
    sudo chmod +x ./gradlew
    sudo chmod +x ./Scripts/Package.ps1

    echo "Generating XCode project..."

    sudo chmod +x ./Vendor/Premake/Mac/premake5
    & "./Vendor/Premake/Mac/premake5" "xcode4"
}

# Linux

elseif ($IsLinux)
{
    sudo chmod +x ./gradlew
    sudo chmod +x ./Scripts/Package.ps1

    echo "Generating GMake project..."

    sudo chmod +x ./Vendor/Premake/Linux/premake5
    & "./Vendor/Premake/Linux/premake5" "gmake2"
}
