package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Geant_des_Bois extends Enemy {

    public Geant_des_Bois() {
        super("Geant_des_Bois", 120);
        super.setWeapon(new Weapon("armeBoss", 50));
    }
}
