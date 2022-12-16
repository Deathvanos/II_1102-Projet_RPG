package com.isep.rpg;

import com.isep.rpg.potion.Grande_potion_de_magie;
import com.isep.rpg.potion.Petite_potion_de_magie;

public abstract class SpellCaster extends Hero{

    private int mana;
    private int consoSort = 10;

    public SpellCaster(String n, int hp) {
        super(n, hp);

    }



    @Override
    public void rempliSacBasique() {
        super.rempliSacBasique();
        // Ajout d'objet pour regen les pv
        super.ajoutObjettoSac(new Petite_potion_de_magie());
        super.ajoutObjettoSac(new Grande_potion_de_magie());
    }


    @Override
    public void upgradeHero(int numChoose) {
        super.upgradeHero(numChoose);
        if (numChoose == 5) {
            // Pour les spellcaster, diminuer le cout en mana des sorts
            this.setConsoSort(this.getConsoSort()-1);

        }
    }

    public int getMana() {return this.mana;}

    public void useMana(int mana) {this.mana -= mana;}
    protected void regenMana(int mana) {this.mana += mana;}

    public void setConsoSort(int newConso) {this.consoSort = newConso;}
    public int getConsoSort() {return this.consoSort;}




}
