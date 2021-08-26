package de.dhbw.fs120.tile;

import javafx.geometry.Rectangle2D;

/**
 * Diese Klasse implementiert das Konzept einer Straße im Rahmen des Spiels. Dies geschieht mithilfe von Street Kacheln auf dem Spielfeld.
 * @author Lena Hammerer, Nick Hillebrand, Fabian Lulikat
 * @version 0.1.1
 */
public class Street extends Tile {
    /**
     * Der Bildausschnitt für die grafische Darstellung der Straße.
     */
    private static final Rectangle2D IMG_VIEW = new Rectangle2D(96*2+32, 96*1+32, 64, 64);

    /**
     * Ist standardmäßig befahrbar.
     */
    public Street() {
        super(IMG_VIEW);
        openToTraffic = true;
        /*
        Eine Idee: Auf Straßen fahren die Fahrzeuge schneller als auf Feldern (müsste vermutlich in Vehicle implementiert werden)
         */
    }
}
