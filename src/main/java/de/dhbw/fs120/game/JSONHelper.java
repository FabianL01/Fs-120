package de.dhbw.fs120.game;

import de.dhbw.fs120.vehicle.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.json.JSONException;

/**
 * Diese Hilfsklasse verarbeitet JSON, sie bietet verschiedene Methoden,
 * um Dateien sowohl auszulesen, also auch zu speichern.
 *
 * @author Jonas Zagst
 * @version 0.1
 */
public class JSONHelper {

    /**
     * Diese Methode dient dem Auslesen von JSON Dateien.
     * @param filePath dieser Parameter repräsentiert den Pfad der auszulesenden Datei.
     *                 Dabei wird dieser relativ zum Projektverzeichnis angegeben.
     *                 Bsp: src/main/resources/json/TemplateGame.json
     * @return der Rückgabewert entspricht dem Inhalt der Datei als JSONObject.
     */
    public JSONObject readJSONFromFile(String filePath){

        try {
            Path path = Path.of(filePath);
            String data = Files.readString(path);
            JSONObject obj = new JSONObject(data);
            return obj;
        } catch (InvalidPathException | NoSuchFileException e){
            System.out.println("Fehler beim Laden der Speicherdatei, die Datei konnte nicht gefunden werden: " + e);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Es ist ein unvorhergesehener Fehler aufgetreten.");
            alert.setContentText("Fehler beim Laden der Speicherdatei, die Datei konnte nicht gefunden werden: " + e);
            alert.showAndWait();

            System.exit(1);
        } catch(IOException | JSONException e) {
            System.out.println("Fehler beim Laden der Speicherdatei zu JSON Objekt: " + e);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Es ist ein unvorhergesehener Fehler aufgetreten.");
            alert.setContentText("Fehler beim Laden der Speicherdatei zu JSON Objekt: " + e);
            alert.showAndWait();

            System.exit(1);
        }
        return new JSONObject();
    }

    /**
     * Diese Methode dient dem schreiben von JSON Dateien.
     * @param filePath dieser Parameter repräsentiert den Pfad der auszulesenden Datei.
     *                 Dabei wird dieser relativ zum Projektverzeichnis angegeben.
     *                 Bsp: src/main/resources/json/TemplateGame.json
     * @param jsonObject dieser Parameter enthält die Daten als JSONObject, welche
     *                   in der Datei gespeichert werden sollen.
     */
    public void writeJSONToFile(String filePath, JSONObject jsonObject){
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e){
            System.out.println("Fehler beim erstellen der Speicherdatei, die Datei konnte nicht gespeichert werden," +
                    "ihr Spielstand könnte verloren gegangen sein: " + e);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Es ist ein unvorhergesehener Fehler aufgetreten.");
            alert.setContentText("Fehler beim erstellen der Speicherdatei, die Datei konnte nicht gespeichert werden," +
                    "ihr Spielstand könnte verloren gegangen sein: " + e);
            alert.showAndWait();

            System.exit(1);
        }

    }
}
