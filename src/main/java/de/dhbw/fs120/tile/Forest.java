package de.dhbw.fs120.tile;

/**
 * Diese Klasse implementiert das Konzept eines Waldes im Rahmen des Spiels. Dies geschieht mithilfe von Forest Kacheln auf dem Spielfeld.
 * Wälder besitzen derzeit nur das Attribut unbefahrbar zu sein.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1
 */
public class Forest extends Tile {

    public Forest() {
        openToTraffic = false;
    }
}
