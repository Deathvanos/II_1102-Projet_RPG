package com.isep.controllers;

import com.isep.MainApplication;
import com.isep.rpg.Combatant;
import com.isep.rpg.Consumable;
import com.isep.rpg.Game;
import com.isep.rpg.Hero;
import com.isep.utils.Utils;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {


    @FXML
    private AnchorPane stage, screenElements;
    @FXML
    private ImageView background;
    private boolean isInit = false;

    @FXML
    private VBox partHeroes, partEnnemy;

    @FXML
    private Label labelRound, labelWave, passageOrder;


    // Les boites de dialogues
    @FXML
    private AnchorPane msgActions, msgAttack, msgDef, msgItem;
    @FXML
    private Label lableActionBox, labelAttkBox, labelDefBox, labelItemBox;
    @FXML
    private VBox lstBtnMechants, lstBtnItems;


    private int nbHeroesPlayed = 0;
    private int numIndexHold = 0;
    private Hero heroTurn;
    private int indexHero;

    // Constructeur
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (Game.option.getNumRound()==1) {
            Game.option.stopMedia();
            Game.option.playMedia(Utils.MusiqueSource.get("Game"));
        } else if (Game.option.getNumRound()==Game.option.getBossRound()) {
            Game.option.stopMedia();
            Game.option.playMedia(Utils.MusiqueSource.get("Boss"));
        }
        // Reste plus qu'a créer les mechants
        // !!!!!! Mettre un boss à la 5 eme Manche !!!!!!
        Game.option.createEnemyWaveArray();
        Game.option.addCopyListHeroes();

        // Initialisation des Combattant et les infos de la game sur l'affichage
        try {
            this.setView("Gentils");
            this.setView("Ennemy");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.newWave();
    }

    private void newWave() {
        // Info manche et vague
        this.changeInfoGame();
        // Info sur l'ordre de passage
        Game.option.defineRunningOrder();
        this.passageOrder.setText("Ordre du tour : " + Game.option.textPassageOrder());
        this.setHerosTurn();
    }




    private void setHerosTurn() {
        // on regarde dans la liste des combattants le premier heros
        for (int pos = numIndexHold; pos < Game.option.getListCombatants().size(); pos++) {
            if (Game.option.isGentilsWin()) {
                this.roundWin();
                return;
            } else if (Game.option.isEnnemyWin()) {
                this.looseView();
                return;
            // On a un Heros
            } else if (Game.option.getListHeroes().contains(Game.option.getListCombatants().get(pos))) {
                this.heroTurn = (Hero) Game.option.getListCombatants().get(pos);
                this.indexHero = pos;
                this.actionBox();
                return;
            }
        }
    }

    private void roundWin() {
        Game.option.addRound();
        Game.option.resetNumWave();
        if (Game.option.isGameOver()) {this.winView();}
        else {this.nextRound();}
    }



    private void nextHero() {
        // Remetre le label affichage selection enemie vide
        labelAttkBox.setText("...");
        this.numIndexHold = this.indexHero;
        // Si tous les heros ont joué une vague
        if (++nbHeroesPlayed >= Game.option.getNbHeroes()) {
            // Les derniers enemys peuvent attaquer
            this.indexHero = Game.option.getListCombatants().size();
            this.attaqueEnnemys();
            this.refreshScreen();

            // Vague suivante : on remet les compteurs à 0
            this.nbHeroesPlayed = 0;
            this.numIndexHold = 0;
            Game.option.addWave();
            this.newWave();// Prochaine action

        }
        else {
            this.numIndexHold++;
            this.setHerosTurn();
        }
    }

    @FXML
    private void actionBox() {
        msgActions.setVisible(true);
        msgAttack.setVisible(false);
        msgDef.setVisible(false);
        msgItem.setVisible(false);
        this.cachebuttonLstMechant();
        this.cachebuttonLstItems();
        this.lableActionBox.setText(this.heroTurn.getName() + " ("+ Utils.getClassName(this.heroTurn.getClass()) + ")");


    }
    @FXML
    private void actionAttk() {
        msgActions.setVisible(false);
        msgAttack.setVisible(true);
        ArrayList<Combatant> listInfo = Game.option.hisCibleEneny(this.heroTurn);

        // Pour les 6 boutons contenu dans le vbox hbox
        // On affiche le nom du mechant
        int temoinNbEnemy = 0;
        for (Node hbox : lstBtnMechants.getChildren()) {
            for (Node button : ((HBox) hbox).getChildren()) {
                if (temoinNbEnemy < listInfo.size()) {
                    Button btn = (Button) button;
                    btn.setVisible(true);
                    Combatant mechant = listInfo.get(temoinNbEnemy);
                    btn.setText(mechant.getName() + " (" + (listInfo.indexOf(mechant)+1) + ")");
                    temoinNbEnemy++;
                }
                else {
                    return;
                }
            }
        }
    }


    @FXML
    private void actionDef() {
        msgActions.setVisible(false);
        msgDef.setVisible(true);
        this.labelDefBox.setText(this.heroTurn.getArmure().getProtection() + " points");
    }

    @FXML
    private void actionItem() {
        msgActions.setVisible(false);
        msgItem.setVisible(true);

        // Pour les 10 boutons contenu dans le vbox hbox
        // On affiche le nom du mechant
        int temoinNbItems= 0;
        for (Node hbox : lstBtnItems.getChildren()) {
            for (Node button : ((HBox) hbox).getChildren()) {
                if (temoinNbItems < this.heroTurn.getSacHero().size()) {
                    Button btn = (Button) button;
                    btn.setVisible(true);
                    Consumable item = this.heroTurn.getSacHero().get(temoinNbItems);
                    btn.setText(item.getName() + " : " + item.getPointRegen() + " " + item.getType());
                    temoinNbItems++;
                }
                else {
                    return;
                }
            }
        }
    }


    @FXML
    void validationAttack() {
        // Les ennemys attack avant
        this.attaqueEnnemys();
        // Si le heros est toujours vivant
        if (Game.option.getListHeroes().contains(this.heroTurn)) {
            // Le heros peut maintenant attaquer
            String[] infoCible = this.labelAttkBox.getText().split(" ");
            for (Combatant cbt: Game.option.hisCibleEneny(this.heroTurn)) {
                // Si l'enemi existe bien
                if (cbt.getName().equals(infoCible[0]) & infoCible[1].equals("(" +(Game.option.hisCibleEneny(this.heroTurn).indexOf(cbt)+1)+")")) {
                    Game.option.actionHeroAttaque(this.heroTurn, cbt);
                    break;
                }
            }
        }
        // Prochaine action
        this.refreshScreen();
        this.nextHero();
    }


    @FXML void validationDefense() {
        // Ajout de la protection
        this.heroTurn.addProtection();
        // Les ennemys attack après
        this.attaqueEnnemys();
        // Fin de la protectino
        this.heroTurn.looseProctection();
        // Prochaine action
        this.refreshScreen();
        this.nextHero();

    }
    @FXML void validationItem() {
        // Utilisation de l'item choisi
        String nameItem = this.labelItemBox.getText().split(" ")[0];
        for (Consumable items: this.heroTurn.getSacHero()) {
            // Si on trouve le bon item alors on l'utilise
            if (items.getName().equals(nameItem)) {
                this.heroTurn.useItem(this.heroTurn.getSacHero().indexOf(items));
                break;
            }
        }
        // Les ennemys attack après
        this.attaqueEnnemys();
        // Prochaine action
        this.refreshScreen();
        this.nextHero();
    }


    private void attaqueEnnemys() {
        for (int pos = this.numIndexHold; pos < this.indexHero; pos++) {
            // Si ce n'est pas un heros mort
            if (Game.option.canAttackEnemy(pos)) {
                Game.option.actionEnemy(Game.option.getListCombatants().get(pos));
            }
        }

    }



    @FXML
    private void enemyChoose(Event e) {
        this.labelAttkBox.setText(((Button) e.getSource()).getText());
    }

    @FXML
    private void itemChoose(Event e) {
        this.labelItemBox.setText(((Button) e.getSource()).getText());
    }

    private void cachebuttonLstMechant() {
        for (Node hbox : lstBtnMechants.getChildren()) {
            for (Node button : ((HBox) hbox).getChildren()) {
                button.setVisible(false);
            }
        }
    }

    private void cachebuttonLstItems() {
        for (Node hbox : lstBtnItems.getChildren()) {
            for (Node button : ((HBox) hbox).getChildren()) {
                button.setVisible(false);
            }
        }
    }


    private void refreshScreen() {
        // Initialisation des Combattant et les infos de la game sur l'affichage
        try {
            this.setView("Gentils");
            this.setView("Ennemy");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





    private void changeInfoGame() {
        this.labelRound.setText("Manche : " + Game.option.getNumRound());
        this.labelWave.setText("Vague : " + Game.option.getNumWave());
    }




    // Pour les les combattants, on va chercher à les afficher
    private void setView(String nameListCbt) throws IOException {
        // Recuperation de la VBox du camp
        ObservableList<Node> campCombattant;
        ArrayList<Combatant> listCbt;
        if (Objects.equals(nameListCbt, "Gentils")) {
            campCombattant = this.partHeroes.getChildren();
            listCbt = Game.option.getListHeroes();
        }
        else if (Objects.equals(nameListCbt, "Ennemy")) {
            campCombattant = this.partEnnemy.getChildren();
            listCbt = Game.option.getListEnemys();
        }
        else return;
        // Nombre temoin de heros qu'on a ajouté sur l'ecran
        int nbCombattantplaced = 0;
        // Parcourir et recuperer tous les enfants et petits enfant de cette VBox
        for (Node hbox : campCombattant) {
            for (Node vbox : ((HBox) hbox).getChildren()) {
                ImageView img = (ImageView) ((VBox) vbox).getChildren().get(0);
                Label label = (Label) ((VBox) vbox).getChildren().get(1);

                if (nbCombattantplaced<listCbt.size()) {
                    // On affiche un hero
                    this.addCbtToScreen(img, label, listCbt, nbCombattantplaced);
                }
                else {
                    // Il n'y a plus de conbattant
                    label.setText("");
                    img.setImage(Utils.setAnImage(Utils.ImageBaseSource.get("Shadow")));
                    img.setFitWidth(Utils.ImageSize.get("Shadow").get(0));
                    img.setFitHeight(Utils.ImageSize.get("Shadow").get(1));
                }
                // Incrementation du nombre de heros affiché et on regarde s'il ont tous été afficher
                nbCombattantplaced++;
            }
        }
    }

    // On afficher un combattant sur l'ecran
    private void addCbtToScreen(ImageView img, Label label, ArrayList<Combatant> listCbt, int arrayPos) throws IOException {
        // Recuperation du Combattant
        Combatant cbt = listCbt.get(arrayPos);
        // Recuperation de la class du combattant
        String typeCombattant = Utils.getClassName(cbt.getClass());
        // nom de l'image
        img.setVisible(true);
        img.setImage(Utils.setAnImage(Utils.ImageBaseSource.get(typeCombattant)));
        img.setFitWidth(Utils.ImageSize.get(typeCombattant).get(0));
        img.setFitHeight(Utils.ImageSize.get(typeCombattant).get(1));
        // Ajout de ses infos
        // si gentils
        if (Game.option.getListHeroes().contains(cbt)) {
            String txt = typeCombattant + ": " + cbt.getName() + "\nPV=" + cbt.getHealthPoint() + " | DPS=" + cbt.getDamage();
            String typeHero = Utils.getClassName(cbt.getClass());
            if (typeHero.equals("Hunter") | typeHero.equals("Mage") | typeHero.equals("Healer")) {
                txt += " | " + ((Hero) cbt).getPrintMunition();
            }
            label.setText(txt);

        }
        //si mechant
        else if (Game.option.getListEnemys().contains(cbt)) {
            label.setText(typeCombattant + ": " + (Game.option.getListEnemys().indexOf(cbt)+1) + "\nPV=" + cbt.getHealthPoint() + " | DPS=" + cbt.getDamage());
        }
    }


    @FXML
    protected void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((Stage) stage.getScene().getWindow()).setScene(scene);
    }

    protected void nextRound() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/reward-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ((Stage) stage.getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void winView() {
        Game.option.stopMedia();
        Game.option.playMedia(Utils.MusiqueSource.get("Win"));
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/win-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ((Stage) stage.getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void looseView() {
        Game.option.stopMedia();
        Game.option.playMedia(Utils.MusiqueSource.get("Loose"));
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/loose-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ((Stage) stage.getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/*
*
*
 */
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

            // Deplacement du label ordre de passe en base à gauche
            passageOrder.setLayoutX(10);
            passageOrder.setLayoutY((int)(stage.getHeight()-passageOrder.getHeight()-5));
            passageOrder.setMaxWidth((int) stage.getWidth()-15);
        }
    }
}


