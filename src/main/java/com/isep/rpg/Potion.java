package com.isep.rpg;

// regen les points de magie
public abstract class Potion extends Consumable{

    private final int effet;

    public Potion(int effet, String name) {
        super(name);
        this.effet = effet;
        super.setType("MP");
    }

    public int getPointRegen() {return this.effet;}

}
