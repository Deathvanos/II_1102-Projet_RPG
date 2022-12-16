package com.isep.rpg;

public abstract class Armor extends Item {

    private final String name;
    private int protection;

    public Armor(String name, int protection) {
        this.name = name;
        this.protection = protection;
    }

    public String getName() {return this.name;}
    public int getProtection() {return this.protection;}

    public void setProtection(int protection) {
        this.protection = protection;
    }
}
