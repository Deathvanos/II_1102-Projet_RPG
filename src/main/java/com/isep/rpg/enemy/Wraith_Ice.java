package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Wraith_Ice extends Enemy {

    public Wraith_Ice() {
        super("Wraith_Ice", 130);
        super.setWeapon(new Weapon("armeBoss", 90));
    }
}
