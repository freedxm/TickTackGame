package com.ticktack.project.model;

public class Player {
    private String name;
    private Type type;

    public Player(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
