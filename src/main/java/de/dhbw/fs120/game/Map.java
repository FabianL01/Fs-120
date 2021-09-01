package de.dhbw.fs120.game;

import de.dhbw.fs120.tile.*;
import de.dhbw.fs120.vehicle.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Diese Klasse beinhaltet die unterschiedlichen Ebenen des Spielfeldes. Zusätzlich wird hier das Laden der
 * Speicherstände behandelt. Es kann entweder ein neues Spielfeld oder eines basierend auf Werten aus dem
 * Speicherstand erstellt werden.
 * Das Spielfeld erbt von {@link StackPane} damit ein einfaches Schichten der unterschiedlichen Ebenen ermöglicht wird.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1.3
 */
public class Map extends StackPane {
    // TODO: Überprüfen ob eine Eigenschaft StackPane mit Getter sinnvoller wäre (siehe auch Game).
    // TODO: Hier fehlen noch einige TryCatch-Blöcke bzw. Fehlerbehandlung
    /**
     * Ein Raster, welches aus den unterschiedlichen Kacheln des Spielfelds aufgebaut ist.
     */
    private GridPane tiles = new GridPane();
    /**
     * Ein Layout-Container für die Positionierung der Gebäude auf dem Spielfeld.
     */
    private Pane buildings = new Pane();
    /**
     * Unterschiedliche, für das Spiel relevante Informationen werden als Text am oberen Spielfeldrand platziert.
     */
    private HBox infoTexts = new HBox();

    /**
     * Im Konstruktor werden die Daten aus der Template-Datei oder ggf. aus der Speicherdatei geladen. Anschließend
     * werden die Layout-Container der unterschiedlichen Ebenen des Spielfelds mit Spielelementen befüllt.
     * @param filename Der Name der Datei welche für die Erzeugung eines neuen Spielfelds geladen werden soll.
     * @param month Der Monat als Property des fiktiven globalen Zeitzyklus.
     * @param player Der Spieler, welcher mit den anderen Spiel-Elementen interagieren soll.
     */
    public Map(String filename, IntegerProperty month, Player player){
        // Lesen der Spieldaten aus der JSON-Datei
        JSONHelper jsonHelper = new JSONHelper();
        JSONObject gameData = (JSONObject) jsonHelper.readJSONFromFile(filename).get("gameData");

        // Der Jahreszyklus startet beim Laden des Spiels in Game standardmäßig mit 1. Hier wird der korrekte Monat aus
        // dem Speicherstand gesetzt
        month.setValue(gameData.getInt("month"));
        // Der Schwierigkeitsgrad wird als String gelesen und in einen Wert der Enum konvertiert
        DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(gameData.getString("difficulty"));

        // Der Player kann erst an dieser Stelle durch eine Methode und nicht über seinen Konstruktor mit den Daten
        // aus der Datei befüllt werden, da er sich für das EventHandling auf der Game Ebenen befinden soll
        JSONObject playerData = (JSONObject) gameData.get("player");
        player.loadPlayerData(playerData.getString("name"), playerData.getDouble("money"), playerData.getInt("xPos"), playerData.getInt("yPos"));

        generateTiles(getTilesFromGameData(gameData));
        generateBuildings(difficultyLevel, month.getValue());
        generateInfoTexts(difficultyLevel, month, player);

        this.getChildren().addAll(tiles, buildings, player, infoTexts);
    }

    /**
     * Diese Hilfsmethode ermöglicht das Ansprechen von Kacheln aus dem Raster über die Spalten- und Reihennummer.
     * @param col Spalte der gesuchten Kachel innerhalb des Rasters
     * @param row Reihe der gesuchten Kachel innerhalb des Rasters
     * @return Die gesuchte Kachel an einer bestimmten Position im Raster
     */
    public Tile getTileAtPos(int col, int row){
        for (Node node : tiles.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                if(node instanceof Tile)    // Typsicherheit (könnte auch kein Tile sein)
                    return (Tile)node;
            }
        }
        return null;
    }

    /**
     * Diese Hilfsmethode wird zur Ermittlung der Anzahl der Felder auf dem Spielfeld, die der Spieler bereits in
     * seinem Besitzt hat, genutzt.
     * @return Anzahl der bereits gekauften Felder.
     */
    public int getNumberOfOwnedFields(){
        int numberOfOwnedFields = 0;
        for (Node node : tiles.getChildren()) {
            if (node instanceof Field){
                if(((Field)node).getStatus() != -1)
                    numberOfOwnedFields++;
            }
        }
        return numberOfOwnedFields;
    }

    /**
     * Diese Methode befüllt den Layout-Container für die Info-Texte mit Labels und den entsprechenden Werten.
     * @param difficultyLevel Der Schwierigkeitsgrad soll während des Spielens erkennbar sein.
     * @param month Der aktuelle fiktive Monat soll angezeigt werden. Binding über Property.
     * @param player Kontostand und Name des Spielers sollen immer ersichtlich sein.
     */
    private void generateInfoTexts(DifficultyLevel difficultyLevel, IntegerProperty month, Player player){
        // Erklärt sich alles soweit von selbst
        // TODO: Überprüfen, ob alle Texte als Property umgesetzt werden sollen oder eine Art updateInfoTexts() Methode gebraucht wird
        Label lblPlayerName = new Label(player.getName() + "'s Farm");
        lblPlayerName.setStyle("-fx-font-size: 20pt");
        Label lblDifficulty = new Label(difficultyLevel.toString());
        lblDifficulty.setStyle("-fx-font-style: italic");

        Label lblMonth = new Label();
        // Monat als Property-Binding damit der Text automatisch aktualisiert wird.
        lblMonth.textProperty().bind(month.asString());

        VBox stats = new VBox();
        Label lblMoney = new Label();
        // Money als Property-Binding damit der Text automatisch aktualisiert wird
        lblMoney.textProperty().bind(Bindings.format("%.2f €", player.money));

        Label lblSiloCornLevel = new Label("Placeholder");

        Label lblCornPrice = new Label("Placeholder");
        Label lblGasPrice = new Label("Placeholder");

        Label lblGasTankLevel = new Label("Placeholder");
        Label lblCornTankLevel = new Label("Placeholder");

        stats.getChildren().addAll(
                new HBox(new Label("Kontostand: ") , lblMoney),
                new HBox(new Label("Silo: ") , lblSiloCornLevel),
                new HBox(new Label("Getreidepreis: ") , lblCornPrice),
                new HBox(new Label("Treibstoffpreis: ") , lblGasPrice),
                new HBox(new Label("Tankanzeige: ") , lblGasTankLevel),
                new HBox(new Label("Geladenes Getreide: ") , lblCornTankLevel));

        infoTexts.setSpacing(250);
        infoTexts.setPadding(new Insets(10, 0, 10, 10));
        infoTexts.getChildren().addAll(new VBox(lblPlayerName, lblDifficulty), new HBox(new Label("Monat: "), lblMonth), stats);
    }

    /**
     * Diese Methode befüllt den Layout-Container für die Ebenen der Gebäude mit den entsprechenden Instanzen.
     * @param difficultyLevel Der Schwierigkeitsgrad wird von den Gebäuden genutzt.
     * @param month Der Monat wird von den Gebäuden genutzt.
     */
    private void generateBuildings(DifficultyLevel difficultyLevel, int month){
        //TODO: Berücksichtigen der Werte aus der Datei für die Gebäude
        Farm farm = new Farm(250); // random Capacity - kommt dann noch mit Balancing
        Store store = new Store(difficultyLevel, month); // das Monatskonzept muss denk ich von außerhalb geregelt werden, sonst benötigt alles PropertyBinding oder EventHandling ..
        GasStation gasStation = new GasStation(1.3, difficultyLevel); // random again
        // immer die gleichen Positionen auf dem Spielfeld
        farm.relocate(Game.TILE_SIZE * 14, Game.TILE_SIZE * 8);
        store.relocate(Game.TILE_SIZE * 2, Game.TILE_SIZE * 16);
        gasStation.relocate(Game.TILE_SIZE * 26, Game.TILE_SIZE * 16);
        this.buildings.getChildren().addAll(farm, store, gasStation);
    }

    /**
     * Diese Methode befüllt das {@link GridPane} für die Kacheln mit Einträgen. Feste Größe ist 30x20.
     * @param tileList Liste von {@link Tile} Objekten die angezeigt werden sollen
     */
    private void generateTiles(ArrayList<Tile> tileList){
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

    /**
     * Diese Hilfsmethode erzeugt aus dem {@link JSONArray} mit den unterschiedlichen Kachel-Typen tatsächliche Kacheln.
     * @param gameData Die Spieldaten aus der Speicherdatei bzw. Template-Datei.
     * @return Eine Liste von unterschiedlichen Kacheln für die Platzierung auf dem Raster des Spielfeldes.
     */
    private ArrayList<Tile> getTilesFromGameData(JSONObject gameData){
        ArrayList<Tile> tileList = new ArrayList<>();
        try {
            JSONArray map = (JSONArray) gameData.get("map");
            for (int i = 0; i < map.length(); i++) {
                JSONObject tile = map.getJSONObject(i);
                // Hier könnte man auch eine Enum einsetzen
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
                        tileList.add(new Field(DifficultyLevel.LEICHT, 30000)); // random again
                        break;
                    default:
                        tileList.add(new Meadow()); // bei null einfach eine Wiese
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return tileList;
    }

    public void saveGame(String filePath){

    }
}
