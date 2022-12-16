package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Orc extends Enemy {

    public Orc() {
        super("Orc", 60);
        super.setWeapon(new Weapon("armeBoss", 20));
    }
}
