package com.isep.rpg;

import com.isep.rpg.food.Lembdas;
import com.isep.rpg.food.Pain;

import java.util.ArrayList;

public abstract class Hero extends Combatant {

    // Creation du sac d'objet
    private final ArrayList<Consumable> sacHero = new ArrayList<>();
    protected Armor armure;
    private double efficaciteConso = 1.0;


    public Hero(String n, int hp) {
        super(n, hp);
    }

    public void setArmure(Armor armure) {
        this.armure = armure;
    }

    public Armor getArmure() {return this.armure;}

    public double getEfficaciteConso() {return this.efficaciteConso;}


    public void rempliSacBasique() {
        // Ajout d'objet pour regen les pv
        this.ajoutObjettoSac(new Pain());
        this.ajoutObjettoSac(new Pain());
        this.ajoutObjettoSac(new Lembdas());
    }
    public void ajoutObjettoSac(Consumable conso) {
        this.sacHero.add(conso);
    }

    public ArrayList<Consumable> getSacHero() {return this.sacHero;}
    public void useItem(int index) {
        Consumable conso = this.sacHero.get(index);
        int point;
        // Verification du type de cosommable
        switch(conso.getType()) {
            case "PV" -> {
                point = (int) Math.round(((Food) conso).getPointRegen() * efficaciteConso);
                super.loosePV(-point);
            }
            case "MP" -> {
                point = (int) Math.round(((Potion) conso).getPointRegen() * efficaciteConso);
                ((SpellCaster) this).regenMana(point);
            }
        }
        // Objet consommé, on le supprime
        this.sacHero.remove(index);
    }


    public abstract String getPrintMunition();
    public abstract void iniSac();

    public void upgradeHero(int numChoose) {
        switch (numChoose) {
            case 1 -> {
                // Augmentation des dégats de l'arme
                // On va appliquer un multiplicateur de dégat à hauteur de 10%
                int newDPS = super.weapon.getDps() + super.weapon.getDps()*10/100;
                super.weapon.setDps(newDPS);
            }
            case 2 -> {
                // Augmentation de la protection de l'armure
                // On ajout +20 en defence
                this.armure.setProtection(this.armure.getProtection()+20);
            }
            case 3 -> {
                // Augmentation du multiplicateur d'efficacité des Potions et Food
                this.efficaciteConso += 0.25;
            }
            case 4 -> {
                // Remet les elements de base contenu dans le sac
                this.iniSac();
            }

        }
    }



    @Override
    public void addProtection() {
        super.protection = this.armure.getProtection();
    }
    public void looseProctection() {super.protection = 0;}



}
