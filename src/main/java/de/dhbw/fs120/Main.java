package de.dhbw.fs120;

import de.dhbw.fs120.game.Map;
import de.dhbw.fs120.tile.Farm;
import de.dhbw.fs120.tile.Meadow;
import de.dhbw.fs120.tile.Tile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        try {
            stage.setTitle("Farming Simulator 120");
            Map gameMap = new Map();
            Scene scene = new Scene(gameMap, 32*30, 32*20);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
