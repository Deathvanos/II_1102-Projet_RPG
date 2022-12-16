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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RewardController {


    @FXML
    private AnchorPane stage, screenElements;
    @FXML
    private ImageView background;
    private boolean isInit = false;

    @FXML
    private ImageView heroImg;

    @FXML
    private Button btn1Attack, btn2Protection, btn3FoodPower, btn4Stuff, btn5Other;

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
            heroImg.setImage(Utils.setAnImage(Utils.ImageBaseSource.get(typeCombattant)));
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


    @FXML
    protected void autoResize(){
        if(stage.getWidth()+5 != background.getFitWidth() | stage.getHeight()+5 != background.getFitHeight()) {
            // Parametre initial non possible dans la fonction init
            if (!this.isInit) {
                background.setPreserveRatio(false);
                ((Stage) stage.getScene().getWindow()).setMinWidth(900);
                ((Stage) stage.getScene().getWindow()).setMinHeight(500);
                this.isInit = true;
            }
            // Aggrandi l'image au max
            background.setFitWidth(stage.getWidth());
            background.setFitHeight(stage.getHeight());
            // Remet à peu près les elements au centre
            double midScreenX = stage.getWidth()/2;
            double midScreenY = stage.getHeight()/2;
            int AnchorPointX = (int) (midScreenX - screenElements.getWidth()/2);
            int AnchorPointY = (int) (midScreenY - screenElements.getHeight()/2);
            screenElements.setLayoutX(AnchorPointX);
            screenElements.setLayoutY(AnchorPointY);
        }
    }


}
