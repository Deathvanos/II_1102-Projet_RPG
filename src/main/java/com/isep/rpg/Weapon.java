//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg;

public class Weapon extends Item{
    String name;
    int dps;

    public Weapon(String name, int dps) {
        this.name = name;
        this.dps = dps;

    }

    public String getName() {return this.name;}

    public int getDps() {return this.dps;}

    public void setDps(int dps) {
        this.dps = dps;
    }
}
