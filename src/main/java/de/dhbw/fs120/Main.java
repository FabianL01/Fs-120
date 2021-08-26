package de.dhbw.fs120;

import de.dhbw.fs120.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        try {
            stage.setTitle("***Farming Simulator 120***");
            Game game = new Game();
            stage.setScene(game.getGameScene());
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
