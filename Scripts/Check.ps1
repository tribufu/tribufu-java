#!/usr/bin/env sh

# Windows

echo "Building for Windows (x86_64)"
cargo check --target x86_64-pc-windows-msvc

echo "Building for Windows (i686)"
cargo check --target i686-pc-windows-msvc

echo "Building for Windows (aarch64)"
cargo check --target aarch64-pc-windows-msvc

# Mac

echo "Building for Mac (x86_64)"
cargo check --target x86_64-apple-darwin

echo "Building for Mac (aarch64)"
cargo check --target aarch64-apple-darwin

# Linux

echo "Building for Linux (x86_64)"
cargo check --target x86_64-unknown-linux-gnu

echo "Building for Linux (i686)"
cargo check --target i686-unknown-linux-gnu

echo "Building for Linux (aarch64)"
cargo check --target aarch64-unknown-linux-gnu

# Android

echo "Building for Android (x86_64)"
cargo check --target x86_64-linux-android

echo "Building for Android (i686)"
cargo check --target i686-linux-android

echo "Building for Android (aarch64)"
cargo check --target aarch64-linux-android

echo "Building for Android (armv7)"
cargo check --target armv7-linux-androideabi
