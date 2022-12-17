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
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController extends ControlleurBase {

    @FXML
    private Slider sliderNbHeros;

    @FXML
    public void initialize() {
        try {Game.option.stopMedia();}
        catch (Exception ignored) {}
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

}