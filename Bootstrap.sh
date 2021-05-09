#!/usr/bin/env sh

sudo chmod +x ./gradlew
sudo chmod +x ./Scripts/Package.ps1

# Linux

if [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]
then

# Mac

elif [ "$(uname)" = "Darwin" ]
then

fi
