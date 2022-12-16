package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Golem_de_Glace extends Enemy {

    public Golem_de_Glace() {
        super("Golem_de_Glace", 300);
        super.setWeapon(new Weapon("armeBoss", 100));
    }
}
