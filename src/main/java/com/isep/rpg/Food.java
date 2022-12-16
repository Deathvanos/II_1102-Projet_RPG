package com.isep.rpg;

// Regen les points de vie
public abstract class Food extends Consumable{

    private final int effet;


    public Food(int effet, String name) {
        super(name);
        this.effet = effet;
        super.setType("PV");
    }

    public int getPointRegen() {
        return this.effet;
    }
}
