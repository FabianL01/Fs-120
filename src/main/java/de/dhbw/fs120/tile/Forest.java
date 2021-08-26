package de.dhbw.fs120.tile;

import javafx.geometry.Rectangle2D;

/**
 * Diese Klasse implementiert das Konzept eines Waldes im Rahmen des Spiels. Dies geschieht mithilfe von Forest Kacheln auf dem Spielfeld.
 * Wälder besitzen derzeit nur das Attribut unbefahrbar zu sein und einen Bildausschnitt.
 * @author Lena Hammerer, Nick Hillebrand, Fabian Lulikat
 * @version 0.1.1
 */
public class Forest extends Tile {
    /**
     * Der Bildausschnitt für die grafische Darstellung des Waldes.
     */
    private static final Rectangle2D IMG_VIEW = new Rectangle2D(96*3+32, 96*4+32, 64, 64);

    /**
     * Der Wald ist standardmäßig nicht befahrbar.
     */
    public Forest() {
        super(IMG_VIEW);
        openToTraffic = false;
    }
}
