package de.dhbw.fs120.vehicle;

/**
 * Sammlung an Methoden für Fahrzeuge, die {@link Attachable} Objekte ziehen können.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public interface Towing {
    /**
     * Ein {@link Attachable} Objekt wir an das Fahrzeug angehängt. Das Fahrzeug ist fortan nicht mehr selbstständg
     * auf dem Spielfeld unterwegs, die Bewegung wird über das angehängte Objekt geregelt, welches dem Spieler
     * zusätzliche Interaktionen ermöglicht.
     */
    public void attach();
}
