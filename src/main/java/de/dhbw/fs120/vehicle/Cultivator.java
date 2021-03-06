package de.dhbw.fs120.vehicle;

/**
 * Ein Cultivator, welcher an einen {@link Tractor} angehängt und vom {@link de.dhbw.fs120.game.Player} gefahren werden kann.
 * Die Aufgabe des Grubbers besteht darin, ein {@link de.dhbw.fs120.tile.Field} umzubrechen, welches abgeernted ist.
 * Anschließend soll das Feld auf den Status zur Aussaht bereit gesetzt werden.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */

public class Cultivator extends Attachable{

    /**
     * Erzeugt einen Cultivator ohne Position.
     */
    public Cultivator(){

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
}
