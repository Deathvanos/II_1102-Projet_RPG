//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg.hero;

import com.isep.rpg.Combatant;
import com.isep.rpg.Hero;

public class Warrior extends Hero {

    public Warrior(String n) {
        super(n, 500);
        this.iniSac();
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }

    @Override
    public void fight(Combatant cible) {
        cible.loosePV(this.weapon.getDps());
    }

    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "loop";
    }

}
