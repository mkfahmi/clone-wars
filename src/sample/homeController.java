package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class homeController {
    @FXML private GridPane gridPaneHome;

    public void initialize() {
        System.out.println("Home Controller");
        gridPaneHome.setStyle("-fx-background-image: url('/img/sky.jpeg'); -fx-background-size:cover");
    }

    @FXML public void toNextPage(ActionEvent event) throws Exception {
        // Change Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseName.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(loader.load(), 1200, 700));

        chooseNameController controller = loader.<chooseNameController>getController();
        stage.show();
    }
}
