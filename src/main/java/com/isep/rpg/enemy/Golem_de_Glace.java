//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg.enemy;

import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Golem_de_Glace extends Enemy {
    public Golem_de_Glace() {
        super("Golem_de_Glace", 300);
        super.setWeapon(new Weapon("armeBoss", 100));
    }
}
