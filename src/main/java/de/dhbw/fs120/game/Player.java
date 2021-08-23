package de.dhbw.fs120.game;

import de.dhbw.fs120.Movable;

/**
 * Spieler der sich auf dem Spielfeld bewegen und mit den unterschiedlichen Spielobjekten interagieren kann.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class Player implements Movable {
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
