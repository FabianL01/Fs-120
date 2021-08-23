package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.Movable;

/**
 * Fahrzeug mit dem sich der Spieler auf dem Spielfeld bewegen kann.
 *
 * Dient als Basis für die unterschiedlichen Fahrzeugtypen innerhalb des Spiels.
 * Es werden die für das Bewegen notwendigen Methoden aus dem {@link Movable} Interface genutzt.
 * Steigt der {@link de.dhbw.fs120.game.Player} in ein Fahrzeug ein, so wird dieser von dem Fahrzeug temporär ersetzt.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.2
 */
public abstract class Vehicle implements Movable {
    /**
     * Horizontale Position des Fahrzeuges in Relation zum Spielfeld.
     * Wert wird bei Instanziierung des Objekts festgelegt und bei der Bewegung dynamisch angepasst.
     */
    private int xPos;
    /**
     * Vertikale Position des Fahrzeuges in Relation zum Spielfeld.
     * Wert wird bei Instanziierung des Objekts festgelegt und bei der Bewegung dynamisch angepasst.
     */
    private int yPos;

    /**
     * Erzeugung eines Fahrzeuges ohne Position.
     *
     */
    public Vehicle(){};

    /**
     * Erzeugung eines Farzeuges an einer bestimmten Position.
     *
     * @param xPos Horizontale Position des Fahrzeuges
     * @param yPos Vertikale Position des Fahrzeuges
     */
    public Vehicle(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Ermöglicht dem Spieler das Verlassen des Fahrzeuges.
     * Das Fahrzeug wird an der aktuellen Position geparkt und der Spieler bewegt sich nun zu Fuß fort.
     */
    public void exit(){};
}
