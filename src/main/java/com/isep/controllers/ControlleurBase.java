//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

package com.isep.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControlleurBase {

    @FXML
    protected AnchorPane stage, screenElements;

    @FXML
    protected ImageView background;

    protected boolean isInit = false;

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
