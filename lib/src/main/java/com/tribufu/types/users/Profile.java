// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.types.users;

import java.time.LocalDate;

public class Profile {
    public long id;
    public String uuid;
    public String name;
    public String displayName;
    public UserType kind;
    public long publicFlags;
    public boolean verified;
    public int level;
    public double experience;
    public boolean publicBirthday;
    public LocalDate birthday;
    public double points;
    public String location;
    public String photoUrl;
    public String bannerUrl;
    public String lastOnline;
    public String biography;
    public int viewCount;
    public String created;
    public String updated;
}
