// Copyright (c) Tribufu. All Rights Reserved.

package com.tribufu.example;

import com.tribufu.TribufuApi;

public class ExampleApp {
    public static void main(String[] args) {
        var api = new TribufuApi();

        var games = api.getGames();
        System.out.println("\n---- GAMES ----");
        for (var game : games) {
            System.out.println(game.name);
        }

        var servers = api.getServers();
        System.out.println("\n---- SERVERS ----");
        for (var server : servers) {
            System.out.println(server.name);
        }
    }
}
