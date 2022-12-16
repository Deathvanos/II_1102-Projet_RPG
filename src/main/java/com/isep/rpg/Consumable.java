package com.isep.rpg;

public abstract class Consumable extends Item{

    private String type;

    protected String name;

    public Consumable (String name) {
        this.name = name;
    }

    public void setType(String t){this.type = t;}

    public String getType(){return this.type;}
    public String getName() {return this.name;}

    public abstract int getPointRegen();




}
