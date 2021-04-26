// Copyright (c) TribuFu. All Rights Reserved

package tribufu.models

class User {
    var id = ""
    var name = ""
    var display_name = ""
    var password_hash = ""
    var role = Role.User
    var confirmed = false
    var two_factor_enabled = false
    var first_name = ""
    var last_name = ""
    var verified = false
    var parental_control = false
    var profile_url = ""
    var public_birthday = false
    var Birthday = ""
    var location = ""
    var timezone = ""
    var language = ""
    var currency = ""
    var theme = Theme.Light
    var ip_address = ""
    var registration_ip_address = ""
    var Joined = ""
    var Updated = ""
    var failed_logins = 0
    var about = ""
    var profile_views = 0
    var content = 0
    var avatar_url = ""
    var background_url = ""
    var status = Status.Offline
    var LastOnline = ""
    var LastActivity = ""
    var LastPost = ""
    var warnings = 0
    var LastWarn = ""
}

enum class Role {
    User,
    Bot,
    Helper,
    Tester,
    Developer,
    Moderator,
    Admin,
    Root
}

enum class Theme {
    Light,
    Dark
}

enum class Status {
    Offline,
    Away,
    Disturb,
    Online,
    Playing
}
