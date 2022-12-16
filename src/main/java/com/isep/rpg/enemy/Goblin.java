package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Goblin extends Enemy {

    public Goblin() {
        super("Goblin", 50);
        super.setWeapon(new Weapon("armeBoss", 10));
    }
}
