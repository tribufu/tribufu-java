// Copyright (c) TribuFu. All Rights Reserved

package com.tribufu.sdk.models

class User {
    val id = ""
    val name = ""
    val display_name = ""
    val password_hash = ""
    val role = Role.User
    val confirmed = false
    val two_factor_enabled = false
    val first_name = ""
    val last_name = ""
    val verified = false
    val parental_control = false
    val profile_url = ""
    val public_birthday = false
    val Birthday = ""
    val location = ""
    val timezone = ""
    val language = ""
    val currency = ""
    val theme = Theme.Light
    val ip_address = ""
    val registration_ip_address = ""
    val Joined = ""
    val Updated = ""
    val failed_logins = 0
    val about = ""
    val profile_views = 0
    val content = 0
    val avatar_url = ""
    val background_url = ""
    val status = Status.Offline
    val LastOnline = ""
    val LastActivity = ""
    val LastPost = ""
    val warnings = 0
    val LastWarn = ""
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
