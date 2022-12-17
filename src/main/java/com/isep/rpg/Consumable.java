//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg;

public abstract class Consumable extends Item{

    private String type;
    protected String name;

    public Consumable (String name) {
        this.name = name;
    }

    // SETTER
    public void setType(String t){this.type = t;}

    // GETTER
    public String getType(){return this.type;}
    public String getName() {return this.name;}
    public abstract int getPointRegen();

}
