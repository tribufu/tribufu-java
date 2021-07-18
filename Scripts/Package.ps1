#!/usr/bin/env pwsh

if ($IsWindows)
{
    python ./Scripts/Package.py
}
else
{
    python3 ./Scripts/Package.py
}
