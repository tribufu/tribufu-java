// Copyright (c) TribuFu. All Rights Reserved

package com.tribufu.sdk.models

class User {
    val id = ""
    val name = ""
    var display_name = ""
    var role = Role.User
    var confirmed = false
    var two_factor_enabled = false
    var first_name = ""
    var last_name = ""
    var verified = false
    var parental_control = false
    var profile_url = ""
    var public_birthday = false
    var birthday = ""
    var location = ""
    var timezone = ""
    var language = ""
    var currency = ""
    var theme = Theme.Light
    var ip_address = ""
    val registration_ip_address = ""
    val Joined = ""
    var Updated = ""
    var failed_logins = 0
    var about = ""
    var profile_views = 0
    var content = 0
    var avatar_url = ""
    var background_url = ""
    var status = Status.Offline
    var last_online = ""
    var last_activity = ""
    var last_post = ""
    var warnings = 0
    var last_warn = ""
}

object Role extends Enumeration {
    val User, Bot, Helper, Tester, Developer, Moderator, Admin, Root = Value
}

object Theme extends Enumeration {
    val Light, Dark = Value
}

object Status extends Enumeration {
    val Offline, Away, Disturb, Online, Playing = Value
}
