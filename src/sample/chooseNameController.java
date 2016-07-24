package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class chooseNameController {
    @FXML private GridPane gridPaneChooseName;
    @FXML private TextField nameInput;

    Player player = Player.getInstance();

    public void initialize() {
        System.out.println("ChooseName Controller");
        gridPaneChooseName.setStyle("-fx-background-image: url('/img/sky.jpeg'); -fx-background-size:cover");
    }

    @FXML public void createCharacter(ActionEvent event) throws Exception {
        if (nameInput.getText().equals("")) {
            return;
        }

        // Set Player name
        player.setName(nameInput.getText());

        // Change Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseCharacter.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(loader.load(), 1200, 700));

        chooseCharacterController controller = loader.<chooseCharacterController>getController();
        controller.setPlayer(player);
        stage.show();
    }
}
