// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.types.servers;

import com.tribufu.types.users.MiniProfile;

public class Server {
    public long id;
    public String name;
    public String description;
    public String address;
    public Short gamePort;
    public short queryPort;
    public Short rconPort;
    public String rconPassword;
    public long packageId;
    public String packageIconUrl;
    public ServerPackage packageObj;
    public String version;
    public Integer clusterId;
    public String websiteUrl;
    public String bannerUrl;
    public Long ownerId;
    public MiniProfile owner;
    public double uptime;
    public String lastOnline;
    public ServerStatus status;
    public Integer ping;
    public String map;
    public Integer usedSlots;
    public Integer maxSlots;
    public String motd;
    public Object players;
    public String country;
    public boolean steam;
    public String discordServerId;
    public String youtubeVideoUrl;
    public Object tags;
    public int commentCount;
    public String secret;
    public String created;
    public String updated;
}
