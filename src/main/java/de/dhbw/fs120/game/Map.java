package de.dhbw.fs120.game;

import de.dhbw.fs120.tile.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Map extends StackPane {
    private static final int TILE_SIZE = 32;
    private GridPane tiles = new GridPane();
    private Pane buildings = new Pane();
    private Pane moveables = new Pane();

    public Map(){
        readMapfromJSON();
        this.getChildren().addAll(tiles, buildings, moveables);
    }

    private void generateBuildings(DifficultyLevel difficultyLevel, int month){
        Farm farm = new Farm(250);
        Store store = new Store(difficultyLevel, month);
        GasStation gasStation = new GasStation(1.3, difficultyLevel);
        farm.relocate(TILE_SIZE * 14, TILE_SIZE * 8);
        store.relocate(TILE_SIZE * 2, TILE_SIZE * 16);
        gasStation.relocate(TILE_SIZE * 26, TILE_SIZE * 16);
        this.buildings.getChildren().addAll(farm, store, gasStation);
    }

    private void generateTiles(ArrayList<Tile> tileList, DifficultyLevel difficultyLevel){
        int x = 0;
        int y = 0;
        for (Tile t : tileList) {
            this.tiles.add(t, x, y);
            x++;
            if(x >= 30){
                x = 0;
                y++;
            }
        }
    }

    private void readMapfromJSON(){
        ArrayList<Tile> tileList = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/json/NewGameSave.json");
            String data = readFromInputStream(inputStream);
            JSONObject jo = new JSONObject(data);
            JSONObject gameData = (JSONObject) jo.get("gameData");
            JSONArray map = (JSONArray) gameData.get("map");
            for (int i = 0; i < map.length(); i++) {
                JSONObject tile = map.getJSONObject(i);
                switch(tile.get("type").toString()){
                    case "Forest":
                        tileList.add(new Forest());
                        break;
                    case "Meadow":
                        tileList.add(new Meadow());
                        break;
                    case "Street":
                        tileList.add(new Street());
                        break;
                    case "Field":
                        tileList.add(new Field(DifficultyLevel.LOW, 20));
                        break;
                    default:
                        tileList.add(new Meadow());
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        generateTiles(tileList, DifficultyLevel.LOW);
        generateBuildings(DifficultyLevel.LOW, 1);
    }
    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
