package com.test2.user;

import java.util.UUID;

public class User {
    public String name;
    public int id;
    public int coins;

    public User(int id, String name, int coins) {
        this.name = name;
        this.id = id;
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
