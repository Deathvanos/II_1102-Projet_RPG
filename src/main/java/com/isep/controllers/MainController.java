package com.isep.controllers;

import com.isep.MainApplication;
import com.isep.rpg.Game;
import com.isep.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane stage, screenElements;
    @FXML
    private ImageView background;
    private boolean isInit = false;

    @FXML
    private Slider sliderNbHeros;



    @FXML
    public void initialize() {
        try {
            Game.option.stopMedia();
        } catch (Exception ignored) {
        }

        Game.playGame();
        Game.option.playMedia(Utils.MusiqueSource.get("Menu"));
    }

    @FXML
    protected void on_Start_ButtonClick() throws IOException {

        // Récureration du nombre de heros pour la partie
        int nbHeroesChoose = (int) sliderNbHeros.getValue();
        Game.option.setNbHeroes(nbHeroesChoose);

        // Chargement de la nouvelle scene
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/heroesCreation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((Stage) stage.getScene().getWindow()).setScene(scene);
    }


    @FXML
    protected void on_Credit_ButtonClick() throws IOException {
        // Chargement de la nouvelle scene
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/credit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((Stage) stage.getScene().getWindow()).setScene(scene);
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