//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg.hero;

import com.isep.rpg.Combatant;
import com.isep.rpg.SpellCaster;

public class Mage extends SpellCaster {

    public Mage(String n) {
        super(n, 400);
        super.regenMana(50);
        this.iniSac();
    }

    @Override
    public void fight(Combatant cible) {
        if (super.getMana() > 0) {
            super.useMana(super.getConsoSort());
            cible.loosePV(super.weapon.getDps());
        }
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }

    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "Mana = " + super.getMana();
    }

}
