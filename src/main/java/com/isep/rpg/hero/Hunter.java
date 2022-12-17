//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg.hero;

import com.isep.rpg.Combatant;
import com.isep.rpg.Hero;

public class Hunter extends Hero {

    private int fleches;
    private final int lotFleche = 15;

    public Hunter(String n) {
        super(n, 450);
        this.iniSac();
        this.addFleches(this.lotFleche);
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }

    @Override
    public void fight(Combatant cible) {
        if (this.fleches > 0) {
            this.fleches--;
            cible.loosePV(this.weapon.getDps());
        }
    }

    public void addFleches(int nbArrows) {this.fleches += nbArrows;}

    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "Flèches = " + this.fleches;
    }

    @Override
    public void upgradeHero(int numChoose) {
        super.upgradeHero(numChoose);
        if (numChoose == 5) {
            // Pour l'archer, rajoute un stocke de fleche
            System.out.println("Stock de flèches : " + this.fleches + " -> " + (this.fleches+this.lotFleche));
            this.addFleches(this.lotFleche);
        }
    }

}