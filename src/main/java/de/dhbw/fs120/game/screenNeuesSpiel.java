package de.dhbw.fs120.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class screenNeuesSpiel extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Farming Simulator");
        BorderPane root = FXMLLoader.load(getClass().getResource("../../../../../resources/de/dhbw/fs120/sceneNeuesSpiel.fxml"));
        Scene scene = new Scene(root, 700, 700);
        stage.setScene(scene);
        stage.show();
    }
}
