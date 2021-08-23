package de.dhbw.fs120.tile;

/**
 * Diese Klasse implementiert das Konzept einer Wiese im Rahmen des Spiels. Dies geschieht mithilfe von Meadow Kacheln auf dem Spielfeld.
 * Derzeit haben Objekte dieser Klasse nur das Attribut befahrbar zu sein.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1
 */
public class Meadow extends Tile {

    public Meadow() {
        openToTraffic = true;
    }
}
