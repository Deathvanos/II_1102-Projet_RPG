package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Minotaur_des_Monts extends Enemy {

    public Minotaur_des_Monts() {
        super("Minotaur_des_Monts", 90);
        super.setWeapon(new Weapon("armeBoss", 50));
    }
}
