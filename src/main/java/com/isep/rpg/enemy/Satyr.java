//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.rpg.enemy;

import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Satyr extends Enemy {
    public Satyr() {
        super("Satyr", 70);
        super.setWeapon(new Weapon("armeBoss", 40));
    }
}
