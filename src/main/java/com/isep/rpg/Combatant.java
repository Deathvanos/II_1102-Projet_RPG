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

    public void setWeapon(Weapon weapon) {this.weapon = weapon;}

    // Classe abstraite
    public abstract void fight(Combatant combatant);
    public abstract int getDamage();
    public abstract void addProtection();

    // Accesseurs GET
    public String getName() {return this.name;}
    public int getHealthPoint() {return this.healthPoint;}
    public int getProtection() {return this.protection;}


    public Weapon getWeapon() {return this.weapon;}


    // http://www.java2s.com/Code/Java/Reflection/Gettheclassnamewithorwithoutthepackage.htm
    public static String getClassName(Class c) {
        String className = c.getName();
        int firstChar;
        firstChar = className.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            className = className.substring(firstChar);
        }
        return className;
    }



}
