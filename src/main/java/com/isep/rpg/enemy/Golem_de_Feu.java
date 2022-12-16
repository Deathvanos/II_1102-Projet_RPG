package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Golem_de_Feu extends Enemy {

    public Golem_de_Feu() {
        super("Golem_de_Feu", 200);
        super.setWeapon(new Weapon("armeBoss", 150));
    }
}
