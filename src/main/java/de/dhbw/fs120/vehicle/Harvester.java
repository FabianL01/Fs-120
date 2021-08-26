package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.CapacityMismatchException;
import de.dhbw.fs120.CornTank;
import de.dhbw.fs120.game.Direction;

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
     * Bei der Erzeugung wird der Treibstofftank voll befüllt und der Getreidetank ist leer.
     */
    public Harvester(){
        gasTank = new GasTank(GAS_CAPACITY, GAS_CAPACITY);
        cornTank = new CornTank(CORN_CAPACITY, 0);
    }

    /**
     * Wenn bei der Bewegung erntebereite {@link de.dhbw.fs120.tile.Field} Felder überfahren werden, sollen diese automatisch
     * abeerntet werden. Der Status des Felds wird zurückgesetzt und der Getreidetank füllt sich.
     * @param direction Die Richtung in welche sich das bewegliche Objekt bewegen soll (Nord, Ost, Süd, West).
     */
    @Override
    public void move(Direction direction) {
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
}
