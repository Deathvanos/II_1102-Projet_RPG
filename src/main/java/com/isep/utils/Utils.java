package com.isep.utils;

import com.isep.MainApplication;
import javafx.scene.image.Image;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Utils {

    // Dico pour récuperer les images les entites
    public static final Map<String, List<Integer>> ImageSize  = new HashMap<String, List<Integer>>() {{
        // Heros
        put("Warrior", Arrays.asList(200, 150));
        put("Hunter", Arrays.asList(200, 200));
        put("Mage", Arrays.asList(100, 100));
        put("Healer", Arrays.asList(200, 200));
        // Ennemy
        put("Fallen_Angel", Arrays.asList(200, 125));
        put("Goblin", Arrays.asList(200, 125));
        put("Orc", Arrays.asList(200, 125));
        put("Satyr", Arrays.asList(200, 125));
        put("Wraith_Ice", Arrays.asList(200, 125));
        put("Wraith_Shadow", Arrays.asList(200, 125));
        put("Geant_des_Montagnes", Arrays.asList(150, 150));
        put("Geant_des_Bois", Arrays.asList(150, 150));
        put("Minotaur_des_Glaces", Arrays.asList(150, 150));
        put("Minotaur_des_Monts", Arrays.asList(150, 150));
        // Boss
        put("Golem_de_Feu", Arrays.asList(350, 350));
        put("Golem_de_Glace", Arrays.asList(350, 350));
        put("Golem_de_Terre", Arrays.asList(350, 350));
        // Ombre de positionnement
        put("Shadow", Arrays.asList(75, 100));
    }};

    // Dico pour récuperer la taille des images voulu
    public static final Map<String, String> ImageBaseSource  = new HashMap<String, String>() {{
        // Heros
        put("Warrior", "images/Heroes/Warrior-Idle.gif");
        put("Hunter", "images/Heroes/Hunter-Idle.gif");
        put("Mage", "images/Heroes/Mage-Idle.gif");
        put("Healer", "images/Heroes/Healer-Idle.gif");

        // Ennemy
        put("Fallen_Angel", "images/Mechants/Idle-Fallen_Angels.gif");
        put("Goblin", "images/Mechants/Idle-Goblin.gif");
        put("Orc", "images/Mechants/Idle-Orc.gif");
        put("Satyr", "images/Mechants/Idle-Satyr.gif");
        put("Wraith_Ice", "images/Mechants/Idle-Wraith_Ice.gif");
        put("Wraith_Shadow", "images/Mechants/Idle-Wraith_Shadow.gif");
        put("Geant_des_Montagnes", "images/Mechants/Idle-Geant_des_Monts.gif");
        put("Geant_des_Bois", "images/Mechants/Idle-Geant_des_Bois.gif");
        put("Minotaur_des_Glaces", "images/Mechants/Idle-Minotaur_des_Glaces.gif");
        put("Minotaur_des_Monts", "images/Mechants/Idle-Minotaur_des_Monts.gif");
        // Boss
        put("Golem_de_Feu", "images/Mechants/Idle-Golem_de_Feu.gif");
        put("Golem_de_Glace", "images/Mechants/Idle-Golem_de_Glace.gif");
        put("Golem_de_Terre", "images/Mechants/Idle-Golem_de_Terre.gif");
        // Ombre de positionnement
        put("Shadow", "images/shadow.png");

    }};


    // Dico pour récuperer la taille des images voulu
    public static final Map<String, String> MusiqueSource  = new HashMap<String, String>() {{
        // Heros
        put("Menu", "musiques/MrKey_Retro-Gaming-Level-Four_LMK.mp3");
        put("Game", "musiques/MrKEY_Play_LMK.mp3");
        put("Boss", "musiques/MrKey_Retro-Gaming_Big-Boss-One_LMK.mp3");
        put("Win", "musiques/MrKEY_Trailer-01_LMK.mp3");
        put("Loose", "musiques/MY-SAD-SONG_Christophe_Espern.mp3");

    }};




    public static Image setAnImage(String path) throws IOException {
        return new Image(Objects.requireNonNull(MainApplication.class.getResource(path)).openStream());
    }


    // http://www.java2s.com/Code/Java/Reflection/Gettheclassnamewithorwithoutthepackage.htm
    public static String getClassName(Class c) {
        String className = c.getName();
        int firstChar;
        firstChar = className.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            className = className.substring(firstChar);
        }
        return className;
    }

}
