#!/usr/bin/env pwsh

# Windows

echo "Building for Windows (x86_64)"
cargo build --target x86_64-pc-windows-msvc $args

echo "Building for Windows (i686)"
cargo build --target i686-pc-windows-msvc $args

echo "Building for Windows (aarch64)"
cargo build --target aarch64-pc-windows-msvc $args

# Mac

echo "Building for Mac (x86_64)"
docker run --rm `
    -v ${pwd}:/Projects/DevKit `
    -v ${home}/.cargo/registry:/usr/local/cargo/registry `
    -w /Projects/DevKit alnilam/mac `
    cargo build --color always --target x86_64-apple-darwin $args

echo "Building for Mac (aarch64)"
docker run --rm `
    -v ${pwd}:/Projects/DevKit `
    -v ${home}/.cargo/registry:/usr/local/cargo/registry `
    -w /Projects/DevKit alnilam/mac `
    cargo build --color always --target aarch64-apple-darwin $args

# Linux

echo "Building for Linux (x86_64)"
docker run --rm `
    -v ${pwd}:/Projects/DevKit `
    -v ${home}/.cargo/registry:/usr/local/cargo/registry `
    -w /Projects/DevKit alnilam/linux:x86_64 `
    cargo build --color always --target x86_64-unknown-linux-gnu $args

echo "Building for Linux (i686)"
docker run --rm `
    -v ${pwd}:/Projects/DevKit `
    -v ${home}/.cargo/registry:/usr/local/cargo/registry `
    -w /Projects/DevKit alnilam/linux:i686 `
    cargo build --color always --target i686-unknown-linux-gnu $args

echo "Building for Linux (aarch64)"
docker run --rm `
    -v ${pwd}:/Projects/DevKit `
    -v ${home}/.cargo/registry:/usr/local/cargo/registry `
    -w /Projects/DevKit alnilam/linux:aarch64 `
    cargo build --color always --target aarch64-unknown-linux-gnu $args

# Android

cargo ndk -t armeabi-v7a -t arm64-v8a -t x86 -t x86_64 build
