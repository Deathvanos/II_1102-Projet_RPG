//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg;

public abstract class Combatant {

    private final String name;
    private int healthPoint;
    protected Weapon weapon;
    protected int protection = 0;

    public Combatant(String n, int hp) {
        this.name = n;
        this.healthPoint = hp;
    }

    public void loosePV(int damage) {
        if (damage - this.protection > 0) {this.healthPoint -= (damage - this.protection );}
        // Regen PV
        else if (damage < 0) {this.healthPoint -= damage;}
    }

    // Abstract Class
    public abstract void fight(Combatant combatant);
    public abstract int getDamage();
    public abstract void addProtection();

    // GETTER
    public String getName() {return this.name;}
    public int getHealthPoint() {return this.healthPoint;}
    public Weapon getWeapon() {return this.weapon;}

    // SETTER
    public void setWeapon(Weapon weapon) {this.weapon = weapon;}

}
