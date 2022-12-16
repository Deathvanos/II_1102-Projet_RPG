package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Geant_des_Montagnes extends Enemy {

    public Geant_des_Montagnes() {
        super("Geant_des_Montagnes", 110);
        super.setWeapon(new Weapon("armeBoss", 60));
    }
}
