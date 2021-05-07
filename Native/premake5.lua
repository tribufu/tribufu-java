project "TribuFu.Jvm"
    location "."
    kind "SharedLib"
    language "C"

    targetdir ("./Binaries/%{cfg.buildcfg}")
    objdir ("./Intermediate/")

    files
    {
        "./Source/**.h",
        "./Source/**.c",
    }

    includedirs
    {
        "../Vendor/TribuFu"
    }

    filter "system:windows"
        staticruntime "Off"
        systemversion "latest"

        links
        {
            "TribuFu.lib"
        }

        libdirs
        {
            "../Vendor/TribuFu/Windows"
        }

    filter { "system:windows", "configurations:debug" }
        runtime "Debug"
        symbols "on"

    filter  { "system:windows", "configurations:release" }
        runtime "Release"
        optimize "on"

    filter "system:linux"
        links
        {
            "TribuFu"
        }

        libdirs
        {
            "../Vendor/TribuFu/Linux"
        }

    filter "system:darwin"
        links
        {
            "TribuFu"
        }

        libdirs
        {
            "../Vendor/TribuFu/Mac"
        }
