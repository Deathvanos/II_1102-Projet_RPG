//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.controllers;

import com.isep.MainApplication;
import com.isep.rpg.Combatant;
import com.isep.rpg.Game;
import com.isep.rpg.Hero;
import com.isep.utils.Utils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class RewardController extends ControlleurBase {

    @FXML
    private ImageView heroImg;

    @FXML
    private Button btn5Other;

    @FXML
    private Label lableName;

    private int counter = 0;

    @FXML
    public void initialize() {
        this.setHerosImage();
    }

    private void setHerosImage() {
        // Recuperation du Combattant
        Combatant heros = Game.option.getListHeroes().get(this.counter);
        // Recuperation de la class du combattant
        String typeCombattant = Utils.getClassName(heros.getClass());
        // Met l'image du 1er heros
        try {
            heroImg.setImage(Utils.setAnImage(Utils.ImageIdleSource.get(typeCombattant)));
            heroImg.setFitWidth(Utils.ImageSize.get(typeCombattant).get(0));
            heroImg.setFitHeight(Utils.ImageSize.get(typeCombattant).get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.lableName.setText("Signé: " + heros.getName() + " le " + typeCombattant);
        btn5Other.setVisible(!typeCombattant.equals("Warrior"));
        if (typeCombattant.equals("Mage") | typeCombattant.equals("Healer")){
            btn5Other.setText("Decrease sort cost");
        } else if (typeCombattant.equals("Hunter")) {
            btn5Other.setText("Add new Arrows");
        }
    }

    @FXML
    private void upgradeChoose(Event e) throws IOException {
        // Choix de l'amelioration
        String choice = ((Button) e.getSource()).getText();
        int numChoice = switch (choice) {
            case "Increase damage" -> 1;
            case "Increase protection" -> 2;
            case "Increase consumable efficiency" -> 3;
            case "Increase Stuff" -> 4;
            case "Decrease sort cost", "Add new Arrows" -> 5;
            default -> 0;
        };
        // Ameliore de hero
        ((Hero) Game.option.getListHeroes().get(this.counter)).upgradeHero(numChoice);
        // Passe au hero suivant
        if (++counter < Game.option.getListHeroes().size())
            this.setHerosImage();
        // Sinon demarre la manche suivante
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/game-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ((Stage) stage.getScene().getWindow()).setScene(scene);
        }
    }

}
