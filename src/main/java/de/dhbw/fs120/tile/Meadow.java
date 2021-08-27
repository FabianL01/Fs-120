package de.dhbw.fs120.tile;

import javafx.geometry.Rectangle2D;

/**
 * Diese Klasse implementiert das Konzept einer Wiese im Rahmen des Spiels. Dies geschieht mithilfe von Meadow Kacheln auf dem Spielfeld.
 * Derzeit haben Objekte dieser Klasse nur das Attribut befahrbar zu sein und einen Bildausschnitt.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.1
 */
public class Meadow extends Tile {
    /**
     * Der Bildausschnitt für die grafische Darstellung des Waldes.
     */
    private static final Rectangle2D IMG_VIEW = new Rectangle2D(32, 32, 64, 64);

    /**
     * Ist standardmäßig befahrbar.
     */
    public Meadow() {
        super(IMG_VIEW);
        openToTraffic = true;
    }
}
