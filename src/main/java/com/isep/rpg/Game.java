package com.isep.rpg;

import com.isep.MainApplication;
import com.isep.rpg.hero.*;
import com.isep.rpg.armor.*;
import com.isep.rpg.weapon.*;
import com.isep.rpg.enemy.*;
import com.isep.utils.Utils;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.*;

public class Game {

    /* _______________________________________________________*/
    /* Transformation de la class Game en une Singleton Class */
    /* _______________________________________________________*/
    // Variable unique Game qui contiendra toutes les données du jeu peu importe la scene
    public static Game option;
    // L'instantiation de la classe Game devient impossible
    private Game() {}
    // Création de l'unique Game - une fois créé, il ne se passera plus rien
    public static void playGame() {
        /* A chaque fois qu''on retourne sur le menu principal, on remet les options du jeu à zero*/
       // if (GameTest.option == null) {
        Game.option = new Game();
       // }
    }

    /* _________________ */
    /* Variable de class */
    /* _________________ */
    private int nbHeroes;
    private int nbHeroesCreate = 1;

    private int numWave = 1;
    private int numRound = 1;
    private final int bossRound = 3;

    private ArrayList<Combatant> listHeroes = new ArrayList<>();
    private ArrayList<Combatant> copyListHeroes;
    private ArrayList<Combatant> listEnemys = new ArrayList<>();
    private ArrayList<Combatant> listCombatants;

    /* _______ */
    /* Getters */
    /* _______ */
    public int getNbHeroes() {return this.nbHeroes;}
    public int getNbHeroesCreate() {return this.nbHeroesCreate;}
    public int getNumWave() {return this.numWave;}
    public int getNumRound() {return this.numRound;}
    public int getBossRound() {return this.bossRound;}
    public ArrayList<Combatant> getListHeroes() {return this.listHeroes;}
    public ArrayList<Combatant> getCopyListHeroes() {return this.copyListHeroes;}
    public ArrayList<Combatant> getListEnemys() {return this.listEnemys;}
    public ArrayList<Combatant> getListCombatants() {return this.listCombatants;}

    /* _______ */
    /* Setters */
    /* _______ */
    public void setNbHeroes(int nbHeroes) {this.nbHeroes = nbHeroes;}
    public void resetNumWave() {this.numWave=1;}



    /* ______ */
    /* Adders */
    /* ______ */
    public void addNbHeroesCreate() {this.nbHeroesCreate++;}
    public void addCopyListHeroes() {this.copyListHeroes = (ArrayList<Combatant>) this.listHeroes.clone();}
    public void addWave() {this.numWave++;}
    public void addRound() {this.numRound++;}


    public void addHeros(String NameClass, String nameHero) {
        switch (NameClass) {
            case "Warrior" -> {
                Warrior newHero = new Warrior(nameHero);
                newHero.setWeapon(new Epee());
                newHero.setArmure(new Armure_en_fer());
                this.listHeroes.add(newHero);
            }
            case "Hunter" -> {
                Hunter newHero = new Hunter(nameHero);
                newHero.setWeapon(new Arc());
                newHero.setArmure(new Armure_en_cuir());
                this.listHeroes.add(newHero);
            }
            case "Mage" -> {
                Mage newHero = new Mage(nameHero);
                newHero.setWeapon(new Baton_de_destruction());
                newHero.setArmure(new Robe_de_sorcier());
                this.listHeroes.add(newHero);
            }
            case "Healer" -> {
                Healer newHero = new Healer(nameHero);
                newHero.setWeapon(new Baton_de_soin());
                newHero.setArmure(new Robe_de_sorcier());
                this.listHeroes.add(newHero);
            }
        }
    }





    /* _______ */
    /* Methodes de class */
    /* _______ */

    public void createEnemyWaveArray() {
        // Les méchants sont selectionné aléatoirement mais ont des armes prédéfinis
        Random ran = new Random();
        // ran appartenant à [min_val, max_val]
        int min_val = 1;
        int max_val = 10;
        if (Game.option.getNumRound() < this.getBossRound()) {
            // Création d'autant d'ennemis que de Héros
            for (int numEnemy = 0; numEnemy < this.nbHeroes; numEnemy++) {
                // Valeur aléatoire
                int x = ran.nextInt(max_val) + min_val;
                // Récupération du mob gagnant
                switch (x) {
                    case 1 -> this.listEnemys.add(new Fallen_Angel());
                    case 2 -> this.listEnemys.add(new Goblin());
                    case 3 -> this.listEnemys.add(new Orc());
                    case 4 -> this.listEnemys.add(new Satyr());
                    case 5 -> this.listEnemys.add(new Wraith_Ice());
                    case 6 -> this.listEnemys.add(new Wraith_Shadow());
                    case 7 -> this.listEnemys.add(new Geant_des_Montagnes());
                    case 8 -> this.listEnemys.add(new Geant_des_Bois());
                    case 9 -> this.listEnemys.add(new Minotaur_des_Glaces());
                    case 10 -> this.listEnemys.add(new Minotaur_des_Monts());
                }
            }
        }
        else {
            // Valeur aléatoire
            int x = ran.nextInt(3) + 1;
            // Récupération du mob gagnant
            switch (x) {
                case 1 -> this.listEnemys.add(new Golem_de_Feu());
                case 2 -> this.listEnemys.add(new Golem_de_Glace());
                case 3 -> this.listEnemys.add(new Golem_de_Terre());
            }
        }
    }




    public void defineRunningOrder(){
        // Concate les deux liste de combattants
        this.listCombatants = (ArrayList<Combatant>) this.listHeroes.clone();
        this.listCombatants.addAll(this.listEnemys);
        // trie au hasars cette liste
        Collections.shuffle(this.listCombatants);
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


    public String textPassageOrder() {
        // [Warriror(toto) - scelteter(1)]
        String txt = "[";
        for (Combatant cbt: this.listCombatants) {
            if (this.listCombatants.indexOf(cbt) == this.listCombatants.size()/2) {
                txt = txt.concat("\n");
            }
            // si gentils
            if (Game.option.getListHeroes().contains(cbt)) {
                txt = txt.concat(getClassName(cbt.getClass()) + "(" + cbt.getName() + ")-");
            }
            //si mechant
            else if (Game.option.getListEnemys().contains(cbt)) {
                txt = txt.concat(getClassName(cbt.getClass()) + "(" + (this.listEnemys.indexOf(cbt)+1) + ")-");
            }

        }
        return txt.substring(0, txt.length() - 1).concat("]");
    }




    public void actionHeroAttaque(Combatant cbt, Combatant cible) {
        cbt.fight(cible);
        // Verification de l'etat de santé de la cible
        verifDeath(cible, "Mechant");
    }





    public void actionEnemy(Combatant cbt) {
        // Choix d'un nombre aleatoire entre 0 et this.listHeros.length
        Random r = new Random ();
        int randomNumber = r.nextInt(this.listHeroes.size());
        // recuperation de la cible
        Combatant cible = listHeroes.get(randomNumber);
        // attaque une cible au hazard
        cbt.fight(cible);
        // Verification de l'etat de santé de la cible
        verifDeath(cible, "Gentil");
    }


    public void verifDeath(Combatant cible, String camp) {
        // Si l'attakant est mort
        if (cible.getHealthPoint() <= 0) {
            ArrayList<Combatant> campList = camp.equals("Gentil") ? this.listHeroes : this.listEnemys;
            // on le vire des deux listes auquel il est consigné
            campList.remove(cible);
        }
    }


    public ArrayList<Combatant> hisCibleEneny(Combatant cbt) {
        if (!Utils.getClassName(cbt.getClass()).equals("Healer"))
            return this.listEnemys;
        return this.listHeroes;
    }
    /* _______ */
    /* Booleen Class */
    /* _______ */

    public boolean isGentilsWin() {
        return this.listEnemys.size() == 0;
    }
    public boolean isEnnemyWin() {
        return this.listHeroes.size() == 0;
    }
    public boolean isGameOver() {
        return this.numRound > this.bossRound;
    }
    public boolean canAttackEnemy(int position) {
        return (
                // Si le mechant n'est pas un heros mort
                !this.getCopyListHeroes().contains(this.getListCombatants().get(position)) &
                // Et si le mechant est vivant
                this.getListEnemys().contains(this.getListCombatants().get(position)) &
                // Et s'il reste des gentils à tuer
                this.listHeroes.size()>0
        );
    }







    private MediaPlayer mediaPlayer;

    public void playMedia(String nameMusique) {
        URL url = MainApplication.class.getResource(nameMusique);
        assert url != null;
        Media media = new Media(url.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stopMedia() {
        mediaPlayer.stop();
    }



}
