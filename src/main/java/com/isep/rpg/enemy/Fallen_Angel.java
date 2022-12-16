package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Fallen_Angel extends Enemy {

    public Fallen_Angel() {
        super("Fallen_Angel", 150);
        super.setWeapon(new Weapon("armeBoss", 70));
    }
}
