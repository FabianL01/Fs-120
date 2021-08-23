package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.Tank;

/**
 * Für die Definition eines Treibstofftanks für ein Fahrzeug. Bei der Bewegung soll Treibstoff verbraucht werden.
 * An der Tankstelle {@link de.dhbw.fs120.tile.GasStation} kann der Tank bis zur maximalen Kapazität aufgefüllt werden.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class GasTank extends Tank {

    /**
     * Bei der Erzeugung eines neuen Tanks müssen die maximale Kapazität sowie der initiale Füllstand festgelegt werden.
     * Die Kapazität kann zu einem späteren Zeitpunkt nicht mehr verändert werden. Der aktuelle Füllstand wird über entsprechende Methoden
     * im Laufe des Spiels nach Bedarf angepasst.
     *
     * @param maxCapacity  Maximale Kapazität die der Tank von der Ressource aufnehmen kann.
     * @param initialLevel Füllstand bei der Initialisierung eines neuen Tanks.
     */
    public GasTank(double maxCapacity, double initialLevel) {
        super(maxCapacity, initialLevel);
    }

}
