package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.CornTank;
import javafx.scene.control.Alert;

/**
 * Ein Kipper, welcher an einen {@link Tractor} angehängt und vom {@link de.dhbw.fs120.game.Player} gefahren werden kann. Der Kipper besitzt einen Tank für Getreide.
 * Die Aufgabe des Kippers besteht darin getreide aus einem {@link Harvester} oder einem Silo der {@link de.dhbw.fs120.tile.Farm} aufzunehmen und für den Transport zu speichern.
 * Weiterhin ist ein Kipper auch in der Lage sein Getreide in einem Silo der {@link de.dhbw.fs120.tile.Farm} zu entladen udn zu speichern oder in einem {@link de.dhbw.fs120.tile.Store} zu verkaufen.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class Trailer extends Attachable {

    /**
     * Maximale Kapazität des Getreidetanks.
     */
    public static final double CORN_CAPACITY = 1000;

    /**
     * Tank für das geerntete Getreide.
     */
    private CornTank cornTank;

    /**
     * Bei der Erzeugung des Trailer wird ein leerer Getreidetank definiert.
     */
    public Trailer(){
        cornTank  = new CornTank(CORN_CAPACITY, 0);
    }

    /**
     * Wenn ein vorhandener Spielstand geladen wird, wird der Kipper mit dem alten Stand von CornTank initialisiert.
     * Mögliche Fehler werden durch einen popup Dialog dem Spieler angezeigt, bevor sich das Programm schließt.
     * @param savedString liefert den alten Stand von CornTank aus der Save Datei.
     */
    public Trailer(String savedString){
        try {
            cornTank = new CornTank(CORN_CAPACITY, Double.valueOf(savedString));
        } catch (NumberFormatException | NullPointerException e){
            System.out.println("Gespeicherte Daten konnten nicht geladen werden: " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Es ist ein unvorhergesehener Fehler aufgetreten.");
            alert.setContentText("Gespeicherte Daten konnten nicht geladen werden: " + e);
            alert.showAndWait();

            System.exit(1);
        }
    }

    @Override
    public void move(String direction) {

    }

    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public void checkInteraction() {

    }

    /**
     * Die toString Methode des Kippers liefert den aktuellen Füllstand von CornTank zurück,
     * so dass dieser gespeichert und später wieder geladen werden kann.
     * @return liefert den Stand von CornTank als String für die Verwendung in der Speicherdatei zurück.
     */
    public String toString(){
        return (String.valueOf(this.cornTank.getCurrentLevel()));
    }
}
