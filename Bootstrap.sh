#!/usr/bin/env sh

sudo chmod +x ./gradlew
sudo chmod +x ./Scripts/Package.ps1

# Linux

if [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]
then
    echo "Generating GMake project..."

    sudo chmod +x ./Vendor/Premake/Linux/premake5
    ./Vendor/Premake/Linux/premake5 gmake2

# Mac

elif [ "$(uname)" = "Darwin" ]
then
    echo "Generating XCode project..."

    sudo chmod +x ./Vendor/Premake/Mac/premake5
    ./Vendor/Premake/Mac/premake5 xcode4
fi
