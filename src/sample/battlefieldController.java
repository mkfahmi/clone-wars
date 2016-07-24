package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class battlefieldController {
    @FXML private GridPane gridPaneBattlefield;
    @FXML private Text namePlayer;
    @FXML private ProgressBar hpPlayer;
    @FXML private Text defPlayer;
    @FXML private Text powerPlayer;
    @FXML private Text forcePlayer;
    @FXML private Text intPlayer;
    @FXML private ImageView imgPlayer;
    @FXML private Text nameEnemy;
    @FXML private ProgressBar hpEnemy;
    @FXML private Text defEnemy;
    @FXML private Text powerEnemy;
    @FXML private Text forceEnemy;
    @FXML private Text intEnemy;
    @FXML private ImageView imgEnemy;
    @FXML private Text textRoomLvl;
    @FXML private Button physicalAttackButton;
    @FXML private Button forceAttackButton;
    @FXML private Button armedAttackButton;
    @FXML private Button newFightButton;
    @FXML private Button nextRoomButton;
    @FXML private TextArea fightHistory;

    private Player player;
    private Character enemy;
    private Room room;
    private int roomLvl = 0;

    public void initialize() {
        System.out.println("Battlefield Controller");
        gridPaneBattlefield.setStyle("-fx-background-image: url('/img/sky.jpeg'); -fx-background-size:cover");
    }

    public void initBattlefield(Player player) {
        this.player = player;
        setPlayer();
        Image image = new Image("/img/" + player.unit.type + ".png");
        imgPlayer.setImage(image);

        room = Room.getInstance(roomLvl);
        this.changeRoom();
    }

    public void setPlayer() {
        namePlayer.setText(player.getName() + " (" + player.unit.type + " de niveau " + player.unit.lvl + ")");
        hpPlayer.setProgress(1);
        defPlayer.setText(Integer.toString(this.player.unit.def));
        forcePlayer.setText(Integer.toString(this.player.unit.force));
        powerPlayer.setText(Integer.toString(this.player.unit.power));
        intPlayer.setText(Integer.toString(this.player.unit.intelligence));
    }

    public void changeRoom() {
        roomLvl += 1;
        room.setLvl(roomLvl);
        textRoomLvl.setText(Integer.toString(roomLvl));

        this.setEnemy();
    }

    public void setEnemy() {
        enemy = room.generateEnemy();

        nameEnemy.setText(enemy.type + " de niveau " + enemy.lvl);
        hpEnemy.setProgress(1);
        defEnemy.setText(Integer.toString(this.enemy.def));
        forceEnemy.setText(Integer.toString(this.enemy.force));
        powerEnemy.setText(Integer.toString(this.enemy.power));
        intEnemy.setText(Integer.toString(this.enemy.intelligence));

        Image image = new Image("/img/" + enemy.type + ".png");
        imgEnemy.setImage(image);

        fightHistory.setText("");

        newFightButton.setVisible(false);
        nextRoomButton.setDisable(true);
        physicalAttackButton.setVisible(true);
        if (player.unit.force != 0) {
            //player has force
            forceAttackButton.setVisible(true);
        }
        else {
            //player don't have force
            armedAttackButton.setVisible(true);
        }
    }

    public void forceAttack(ActionEvent event) {
        int old_hp = enemy.hp;
        int enemy_hp = player.unit.forceAttack(enemy);
        fightHistory.setText(fightHistory.getText() + "Vous avez utlisé Force attack et enlevé " + (old_hp - enemy_hp) + " hp à votre ennemi.\n");
        enemyTurn(event, enemy_hp);
    }

    public void armedAttack(ActionEvent event) {
        int old_hp = enemy.hp;
        int enemy_hp = player.unit.armedAttack(enemy);
        fightHistory.setText(fightHistory.getText() + "Vous avez utlisé Armed attack et enlevé " + (old_hp - enemy_hp) + " hp à votre ennemi.\n");
        enemyTurn(event, enemy_hp);
    }

    public void physicalAttack(ActionEvent event) {
        int old_hp = enemy.hp;
        int enemy_hp = player.unit.physicalAttack(enemy);
        fightHistory.setText(fightHistory.getText() + "Vous avez utlisé Physical attack et enlevé " + (old_hp - enemy_hp) + " hp à votre ennemi.\n");
        enemyTurn(event, enemy_hp);
    }

    public void enemyTurn(ActionEvent event, int enemy_hp) {
        hpEnemy.setProgress((float) enemy_hp/enemy.hpMax);
        if (enemy.hp > 0) {
            int old_hp = enemy.hp;
            int new_hp = this.enemyAttack();
            fightHistory.setText(fightHistory.getText() + "Votre adversaire vous a infligé " + Math.abs(old_hp - new_hp) + " dégâts.\n");
            hpPlayer.setProgress((float) new_hp/player.unit.hpMax);
            if (new_hp <= 0)
                endGame(event);
        }
        else
            this.endFight(enemy);
    }

    public int enemyAttack() {
        int i = new Random().nextInt(2) + 1;
        int pv;
        if (i == 1) {
            pv = enemy.physicalAttack(player.unit);
        }
        else {
            pv = enemy.armedAttack(player.unit);
        }

        return pv;
    }

    public void endFight(Character enemy) {

        physicalAttackButton.setVisible(false);
        armedAttackButton.setVisible(false);
        forceAttackButton.setVisible(false);

        //TODO real mob xp
        int mobXp = 60;
        System.out.println((((mobXp*enemy.lvl)/5)*((Math.pow(2*(enemy.lvl+10), 2.5)/(Math.pow((enemy.lvl*player.unit.lvl+10), 2.5)))+1)));
        System.out.println("\n");
        System.out.println(player.unit.xpMax);

        player.unit.xp += (((mobXp*enemy.lvl)/5)*((Math.pow(2*(enemy.lvl+10), 2.5)/(Math.pow((enemy.lvl*player.unit.lvl+10), 2.5)))+1));
        player.unit.lvlUp();
        System.out.println(player.unit.lvl);
        setPlayer();
        newFightButton.setVisible(true);
        nextRoomButton.setDisable(false);
    }

    public void endGame(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            stage.setScene(new Scene(loader.load(), 1200, 700));
        } catch (IOException e) {
            e.printStackTrace();
        }

        homeController controller = loader.<homeController>getController();
        stage.show();
    }



}
