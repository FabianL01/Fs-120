package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.CapacityMismatchException;
import de.dhbw.fs120.game.Direction;

/**
 * Tranktor mit dem sich der Spieler auf dem Spielfeld bewegen kann. Bei der Bewegung verbraucht der Traktor Treibstoff
 * aus seinem Treibstofftank. Der Traktor kann unterschiedliche {@link Attachable} Objekte an sich anhängen, um diese
 * auf dem Spielfeld zu bewegen.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class Tractor extends Vehicle implements Towing {
    /**
     * Maximale Kapazität des Treibstofftanks.
     */
    public static final double GAS_CAPACITY = 200;
    /**
     * Durchschnittlicher Verbrauch pro Bewegungseinheit.
     */
    public static final double GAS_CONSUMPTION = 1.5;
    /**
     * Tank für den Treibstoff.
     */
    private GasTank gasTank;

    /**
     * Bei der Erzeugung wird der Treibstofftank voll befüllt.
     */
    public Tractor(){
        gasTank = new GasTank(GAS_CAPACITY, GAS_CAPACITY);
    }

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

    @Override
    public void attach() {

    }

}
