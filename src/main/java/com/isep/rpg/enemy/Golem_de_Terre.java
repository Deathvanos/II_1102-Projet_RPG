package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Golem_de_Terre extends Enemy {

    public Golem_de_Terre() {
        super("Golem_de_Terre", 400);
        super.setWeapon(new Weapon("armeBoss", 50));
    }
}
