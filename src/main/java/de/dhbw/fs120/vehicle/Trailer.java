package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.CornTank;
import de.dhbw.fs120.game.Direction;

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

    @Override
    public void move(Direction direction) {

    }

    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public void checkInteraction() {

    }
}
