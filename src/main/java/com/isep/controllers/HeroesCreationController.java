//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.controllers;

import com.isep.MainApplication;
import com.isep.rpg.Game;
import com.isep.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HeroesCreationController extends ControlleurBase implements Initializable {

     @FXML
    private Label infoNbHeroe;
    @FXML
    private ImageView warrior, hunter, mage, healer;

    @FXML
    private TextField nameHeroe;
    @FXML
    private Button next;
    @FXML
    private Label labelError;

    // Attribut de la class
    private String HeroChoose;

    // Constructeur
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  playMedia();
        infoNbHeroe.setText("Héros " + Game.option.getNbHeroesCreate() + "/" + Game.option.getNbHeroes());
        if (Game.option.getNbHeroesCreate() >= Game.option.getNbHeroes()) next.setText("Jouer");
        else next.setText("Suivant");
    }

    /* Deux fonctions pour faciliter la création d'images*/
    private void defaultImage() throws IOException {
        warrior.setImage(Utils.setAnImage("images/HeroesCreation/WarriorIdle.png"));
        hunter.setImage(Utils.setAnImage("images/HeroesCreation/HunterIdle.png"));
        mage.setImage(Utils.setAnImage("images/HeroesCreation/MageIdle.png"));
        healer.setImage(Utils.setAnImage("images/HeroesCreation/HealerIdle.png"));
    }

    /* Quand on selectionne une image */
    @FXML
    protected void onImageWarriorButtonClick() throws IOException {
        this.defaultImage();
        warrior.setImage(Utils.setAnImage("images/HeroesCreation/WarriorClic.png"));
        this.HeroChoose = "Warrior";
    }
    @FXML
    protected void onImageHunterButttonClick() throws IOException {
        this.defaultImage();
        hunter.setImage(Utils.setAnImage("images/HeroesCreation/HunterClic.png"));
        this.HeroChoose = "Hunter";
    }
    @FXML
    protected void onImageMageButttonClick() throws IOException {
        this.defaultImage();
        mage.setImage(Utils.setAnImage("images/HeroesCreation/MageClic.png"));
        this.HeroChoose = "Mage";
    }
    @FXML
    protected void onImageHealerButttonClick() throws IOException {
        this.defaultImage();
        healer.setImage(Utils.setAnImage("images/HeroesCreation/HealerClic.png"));
        this.HeroChoose = "Healer";
    }

    /* Quand la sourie rentre dans une image */
    @FXML
    protected void onImageWarriorButtonHoover() throws IOException {
        warrior.setImage(Utils.setAnImage("images/HeroesCreation/WarrioOnMouse.png"));
    }
    @FXML
    protected void onImageHunterButttonHoover() throws IOException {
        hunter.setImage(Utils.setAnImage("images/HeroesCreation/HunterOnMouse.png"));
    }
    @FXML
    protected void onImageMageButttonHoover() throws IOException {
        mage.setImage(Utils.setAnImage("images/HeroesCreation/MageOnMouse.png"));
    }
    @FXML
    protected void onImageHealerButttonHoover() throws IOException {
        healer.setImage(Utils.setAnImage("images/HeroesCreation/HealerOnMouse.png"));
    }

    /* Quand la sourie quite une image */
    @FXML
    protected void onImageWarriorButtonQuit() throws IOException {
        if (!Objects.equals(this.HeroChoose, "Warrior")) warrior.setImage(Utils.setAnImage("images/HeroesCreation/WarriorIdle.png"));
        else warrior.setImage(Utils.setAnImage("images/HeroesCreation/WarriorClic.png"));
    }
    @FXML
    protected void onImageHunterButttonQuit() throws IOException {
        if (!Objects.equals(this.HeroChoose, "Hunter")) hunter.setImage(Utils.setAnImage("images/HeroesCreation/HunterIdle.png"));
        else hunter.setImage(Utils.setAnImage("images/HeroesCreation/HunterClic.png"));
    }
    @FXML
    protected void onImageMageButttonQuit() throws IOException {
        if (!Objects.equals(this.HeroChoose, "Mage")) mage.setImage(Utils.setAnImage("images/HeroesCreation/MageIdle.png"));
        else mage.setImage(Utils.setAnImage("images/HeroesCreation/MageClic.png"));
    }
    @FXML
    protected void onImageHealerButttonQuit() throws IOException {
        if (!Objects.equals(this.HeroChoose, "Healer")) healer.setImage(Utils.setAnImage("images/HeroesCreation/HealerIdle.png"));
        else healer.setImage(Utils.setAnImage("images/HeroesCreation/HealerClic.png"));
    }

    @FXML
    protected void onPlayButtonClick() throws IOException {
        if (this.HeroChoose != null & !Objects.equals(nameHeroe.getText(), "")) {
            // Création du dernier heros
            Game.option.addHeros(this.HeroChoose, nameHeroe.getText());
            // Ajout de +1 Hero au compteur
            Game.option.addNbHeroesCreate();
            if (Game.option.getNbHeroesCreate() <= Game.option.getNbHeroes()) {
                // Chargement de la page de jeu
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/heroesCreation-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ((Stage) stage.getScene().getWindow()).setScene(scene);
            } else {
                // Chargement de la page de jeu
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/game-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ((Stage) stage.getScene().getWindow()).setScene(scene);
            }
        }
        else labelError.setText("Classe ou Nom du Heros inexistant");
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        // Abandon du jeu -> Retour menu principal
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((Stage) stage.getScene().getWindow()).setScene(scene);
    }

}