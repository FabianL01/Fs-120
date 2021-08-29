package de.dhbw.fs120.game;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 *
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.5
 */
public class StartScreen extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Farming Simulator");
                BorderPane root = FXMLLoader.load(getClass().getResource("../../../../../resources/de/dhbw/fs120/sceneStart.fxml"));
        Scene scene = new Scene(root, 700, 700);
        stage.setScene(scene);
        stage.show();
    }
}
