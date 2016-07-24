package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class chooseCharacterController {
    @FXML private GridPane gridPaneChooseCharacter;
    @FXML private ProgressBar hpbar;
    @FXML private ProgressBar defbar;
    @FXML private ProgressBar powerbar;
    @FXML private ProgressBar forcebar;
    @FXML private ProgressBar intbar;

    private Player player;

    public void initialize() {
        System.out.println("Choose Character Controller");
        gridPaneChooseCharacter.setStyle("-fx-background-image: url('/img/sky.jpeg'); -fx-background-size:cover");
    }

    public void setPlayer(Player player) {
        this.player = player;
        System.out.println(player.getName());
        this.player.unit = null;
    }

    @FXML public void jediChoose(MouseEvent evt) {
        hpbar.setProgress(0.125);
        defbar.setProgress(0.25);
        powerbar.setProgress(0.125);
        forcebar.setProgress(0.25);
        intbar.setProgress(0.25);
        player.unit = new Jedi();
    }

    @FXML public void sithChoose(MouseEvent evt) {
        hpbar.setProgress(0.125);
        defbar.setProgress(0.125);
        powerbar.setProgress(0.25);
        forcebar.setProgress(0.25);
        intbar.setProgress(0.25);
        player.unit = new Sith();
    }

    @FXML public void bountyhunterChoose(MouseEvent evt) {
        hpbar.setProgress(0.15);
        defbar.setProgress(0.15);
        powerbar.setProgress(0.15);
        forcebar.setProgress(0);
        intbar.setProgress(0.54);
        player.unit = new BountyHunter();
    }

    @FXML public void imperialAgentChoose(MouseEvent evt) {
        hpbar.setProgress(0.54);
        defbar.setProgress(0.15);
        powerbar.setProgress(0.15);
        forcebar.setProgress(0);
        intbar.setProgress(0.15);
        player.unit = new ImperialAgent();
    }

    @FXML public void mercenaryChoose(MouseEvent evt) {
        hpbar.setProgress(0.15);
        defbar.setProgress(0.54);
        powerbar.setProgress(0.125);
        forcebar.setProgress(0);
        intbar.setProgress(0.15);
        player.unit = new Mercenary();
    }

    @FXML public void jawaChoose(MouseEvent evt) {
        hpbar.setProgress(0.25);
        defbar.setProgress(0.25);
        powerbar.setProgress(0.25);
        forcebar.setProgress(0);
        intbar.setProgress(0.25);
        player.unit = new Jawa();
    }

    @FXML public void createCharacter(ActionEvent event) throws Exception {

        if (player.unit == null) {
            return;
        }

        // Change Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("battlefield.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(loader.load(), 1200, 700));

        battlefieldController controller = loader.<battlefieldController>getController();
        controller.initBattlefield(player);
        stage.show();
    }

}
