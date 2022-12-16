package com.isep.rpg.hero;

import com.isep.rpg.Combatant;
import com.isep.rpg.Game;
import com.isep.rpg.Hero;


public class Warrior extends Hero {



    public Warrior(String n) {
        super(n, 500);
        this.iniSac();
    }


    @Override
    public void fight(Combatant cible) {
        cible.loosePV(this.weapon.getDps());
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }

    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "loop";
    }




}
