package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.CapacityMismatchException;
import de.dhbw.fs120.CornTank;
import javafx.scene.control.Alert;

/**
 * Ein Mähdrescher, welcher vom Spieler gefahren werden kann. Das Fahrzeug besitzt einen Tank für Treibstoff und Getreide.
 * Die Aufgabe des Mähdreschers besteht darin, ein {@link de.dhbw.fs120.tile.Field} abzuerten, die bereit für die Ernte sind und das Getreide in seinem
 * Tank zwischenzulagern bis es in den {@link Trailer} oder in das Silo der {@link de.dhbw.fs120.tile.Farm} entladen werden kann.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class Harvester extends Vehicle {
    /**
     * Maximale Kapazität des Getreidetanks.
     */
    public static final double CORN_CAPACITY = 500;
    /**
     * Maximale Kapazität des Treibstofftanks.
     */
    public static final double GAS_CAPACITY = 100;
    /**
     * Durchschnittlicher Verbrauch pro Bewegungseinheit.
     */
    public static final double GAS_CONSUMPTION = 1;

    /**
     * Tank für das geerntete Getreide.
     */
    private CornTank cornTank;
    /**
     * Tank für den Treibstoff.
     */
    private GasTank gasTank;

    /**
     * Bei der Erzeugung von einem neuen Spiel wird der Treibstofftank standardmäßig voll befüllt und der Getreidetank ist leer.
     */
    public Harvester(){
        gasTank = new GasTank(GAS_CAPACITY, GAS_CAPACITY);
        cornTank = new CornTank(CORN_CAPACITY, 0);
    }

    /**
     * Wenn ein vorhandener Spielstand geladen wird, wird der Mähdrescher mit dem alten Stand
     * von GasTank und CornTank initialisiert.
     * Mögliche Fehler werden durch einen popup Dialog dem Spieler angezeigt, bevor sich das Programm schließt.
     * @param savedString liefert den alten Stand von GasTank und CornTank aus der Save Datei.
     */
    public Harvester(String savedString){
        String[] saves = savedString.split(";");

        try {
            gasTank = new GasTank(GAS_CAPACITY, Double.valueOf(saves[0]));
            cornTank = new CornTank(CORN_CAPACITY, Double.valueOf(saves[1]));
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

    /**
     * Wenn bei der Bewegung erntebereite {@link de.dhbw.fs120.tile.Field} Felder überfahren werden, sollen diese automatisch
     * abeerntet werden. Der Status des Felds wird zurückgesetzt und der Getreidetank füllt sich.
     * @param direction Die Richtung in welche sich das bewegliche Objekt bewegen soll (Nord, Ost, Süd, West).
     */
    @Override
    public void move(String direction) {
        try {
            gasTank.reduce(GAS_CONSUMPTION);
        } catch(CapacityMismatchException e){

        }
    }

    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public void checkInteraction() {

    }

    /**
     * Die toString Methode des Mähdreschers liefert den GasTank, sowie den CornTank Stand,
     * so dass diese gespeichert und später wieder geladen werden können.
     * @return liefert den Stand von GasTank und CornTank als String für die Verwendung in der Speicherdatei zurück.
     */
    public String toString(){
        return (String.valueOf(this.gasTank.getCurrentLevel()) + ";" +
                String.valueOf(this.cornTank.getCurrentLevel()));
    }
}
