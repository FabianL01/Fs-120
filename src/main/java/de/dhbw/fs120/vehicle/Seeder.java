package de.dhbw.fs120.vehicle;

/**
 * Eine Sämaschine, welche an einen {@link Tractor} angehängt und vom {@link de.dhbw.fs120.game.Player} gefahren werden kann.
 * Die Aufgabe der Sämaschine besteht darin, Getreide auf einem {@link de.dhbw.fs120.tile.Field} auszusähen, welches zur Aussaht bereit ist.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class Seeder extends Attachable{

    /**
     * Erzeugt einen Seeder ohne Position.
     */
    public Seeder(){

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
