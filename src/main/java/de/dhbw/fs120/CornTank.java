package de.dhbw.fs120;

/**
 * Für die Definition eines Getreidetanks für ein Spielobjekte. Der Tank kann befüllt oder entleert werden.
 * Einsatzzwecke umfassen Fahrzeuge und Gebäude.
 *
 * @author Lena Hammerer
 * @version 0.1
 */
public class CornTank extends Tank{
    /**
     * Bei der Erzeugung eines neuen Tanks müssen die maximale Kapazität sowie der initiale Füllstand festgelegt werden.
     * Die Kapazität kann zu einem späteren Zeitpunkt nicht mehr verändert werden. Der aktuelle Füllstand wird über entsprechende Methoden
     * im Laufe des Spiels nach Bedarf angepasst.
     *
     * @param maxCapacity  Maximale Kapazität die der Tank von der Ressource aufnehmen kann.
     * @param initialLevel Füllstand bei der Initialisierung eines neuen Tanks.
     */
    public CornTank(double maxCapacity, double initialLevel) {
        super(maxCapacity, initialLevel);
    }
}
