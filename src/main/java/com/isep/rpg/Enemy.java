//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg;

public abstract class Enemy extends Combatant{

    public Enemy(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant cible) {
        cible.loosePV(this.weapon.getDps());

    }

    @Override
    public void addProtection() {
        super.protection = 0;
    }

    public int getDamage() {return this.weapon.getDps();}
}
