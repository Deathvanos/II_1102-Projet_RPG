//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

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
