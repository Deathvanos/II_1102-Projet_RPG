package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Wraith_Shadow extends Enemy {

    public Wraith_Shadow() {
        super("Wraith_Shadow", 140);
        super.setWeapon(new Weapon("armeBoss", 80));
    }
}
