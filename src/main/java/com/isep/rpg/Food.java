//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg;

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
