package com.isep.rpg.hero;
import com.isep.rpg.Combatant;
import com.isep.rpg.SpellCaster;


public class Healer extends SpellCaster {




    public Healer(String n) {
        super(n, 600);
        super.regenMana(200);
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
    public int getDamage() {return super.weapon.getDps();}


    @Override
    public String getPrintMunition() {
        return "Mana = " + super.getMana();
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }




}
