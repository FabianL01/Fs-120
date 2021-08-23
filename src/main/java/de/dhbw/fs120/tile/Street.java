package de.dhbw.fs120.tile;

/**
 * Diese Klasse implementiert das Konzept einer Straße im Rahmen des Spiels. Dies geschieht mithilfe von Street Kacheln auf dem Spielfeld.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1
 */
public class Street extends Tile {

    public Street() {
        openToTraffic = true;
        /*
        Eine Idee: Auf Straßen fahren die Fahrzeuge schneller als auf Feldern (müsste vermutlich in Vehicle implementiert werden)
         */
    }
}
