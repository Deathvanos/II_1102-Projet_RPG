package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Satyr extends Enemy {

    public Satyr() {
        super("Satyr", 70);
        super.setWeapon(new Weapon("armeBoss", 40));
    }
}
