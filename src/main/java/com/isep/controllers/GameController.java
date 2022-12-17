//***** II.1102 – Algorithmique et Programmation - Projet : Mini RPG Lite 3000 *****
// ISEP - A1 - G7C
// Auteur : Charles_Mailley
// Date de rendu  : 17/12/2022

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

public class GameController extends ControlleurBase implements Initializable {

    @FXML
    private VBox partHeroes, partEnnemy;

    @FXML
    private Button menuP;

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

    private ArrayList<VBox> lstBlocImageGentil = new ArrayList<>();
    private ArrayList<VBox> lstBlocImageMechant = new ArrayList<>();

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
        implementBoxToCombattant(this.partHeroes, this.lstBlocImageGentil);
        implementBoxToCombattant(this.partEnnemy, this.lstBlocImageMechant);
        this.refreshScreen();
        // DDébut de la manche
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
                //this.setImageAttkGentilCbt(this.heroTurn);
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
        this.refreshScreen();
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
        //this.refreshScreen();
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
        setView(Game.option.getListHeroes(), this.lstBlocImageGentil);
        setView(Game.option.getListEnemys(), this.lstBlocImageMechant);
    }

    private void changeInfoGame() {
        this.labelRound.setText("Manche : " + Game.option.getNumRound());
        this.labelWave.setText("Vague : " + Game.option.getNumWave());
    }

    private void implementBoxToCombattant(VBox partCamp, ArrayList<VBox> listblocInfoCbt) {
        // Recupere les ImagesView et Label de chaque combattant dans une liste
        for (Node hbox : partCamp.getChildren()) {
            for (Node vbox : ((HBox) hbox).getChildren()) {
                listblocInfoCbt.add((VBox) vbox);
            }
        }

    }

    private void cleanScreenImg(ArrayList<VBox> cadreCbt) {
        for (VBox box: cadreCbt) {
            // Recuperation des elements
            ImageView img = (ImageView) box.getChildren().get(0);
            Label label = (Label) box.getChildren().get(1);
            // Mise à blanc des elements
            label.setText("");
            try {
                img.setImage(Utils.setAnImage(Utils.ImageIdleSource.get("Shadow")));
                img.setFitWidth(Utils.ImageSize.get("Shadow").get(0));
                img.setFitHeight(Utils.ImageSize.get("Shadow").get(1));
            } catch (IOException ignored) {}
        }
    }

    // Pour les les combattants, on va chercher à les afficher
    private void setView(ArrayList<Combatant> listCamp, ArrayList<VBox> listInfo) {
        // Vide l'ecran
        this.cleanScreenImg(listInfo);
        // Remet les combattant encore vivant
        for (int posCbt = 0; posCbt < listCamp.size(); posCbt++) {
            // Recuperation de la boite d'affichage Combattant, sa boite image et boite label
            ObservableList<Node> blocInfoCbt = listInfo.get(posCbt).getChildren();
            ImageView img = (ImageView) blocInfoCbt.get(0);
            Label label = (Label) blocInfoCbt.get(1);
            // Recuperation du combattant
            Combatant cbt = listCamp.get(posCbt);

            this.addCbtToScreen(img, label, cbt);
        }
    }

    // On afficher un combattant sur l'ecran
    private void addCbtToScreen(ImageView img, Label label, Combatant cbt) {
        // Recuperation de la class du combattant
        String typeCombattant = Utils.getClassName(cbt.getClass());
        // On affiche l'image du combattant
        try {
            img.setVisible(true);
            img.setImage(Utils.setAnImage(Utils.ImageIdleSource.get(typeCombattant)));
            img.setFitWidth(Utils.ImageSize.get(typeCombattant).get(0));
            img.setFitHeight(Utils.ImageSize.get(typeCombattant).get(1));
        } catch (IOException ignored) {}
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


    private void setImageAttkGentilCbt(Combatant cbt) {
        String typeCombattant = Utils.getClassName(cbt.getClass());
        ImageView img = (ImageView) this.lstBlocImageGentil.get(Game.option.getListHeroes().indexOf(cbt)).getChildren().get(0);
        try {
            img.setVisible(true);
            img.setImage(Utils.setAnImage(Utils.ImageAttackSource.get(typeCombattant)));
            img.setFitWidth(Utils.ImageSize.get(typeCombattant).get(0));
            img.setFitHeight(Utils.ImageSize.get(typeCombattant).get(1));
        } catch (IOException ignored) {}
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

    @Override
    protected void autoResize() {
        super.autoResize();
        // Deplacement du label ordre de passe en base à gauche
        passageOrder.setLayoutY((int)(stage.getHeight()-passageOrder.getHeight()-5));
        passageOrder.setMaxWidth((int) stage.getWidth()-15);
        //Deplacement du bouton menu
        menuP.setLayoutX((int) stage.getWidth()-165);
    }

}


/*
* Quand c'est au tour d'un hero on lui change l'image
* L'image reviendra à la normal apres avoir jouer
 */