package de.dhbw.fs120.tile;

import de.dhbw.fs120.CornTank;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Diese Klasse implementiert das Konzept der Getreidelagerung. Dies geschieht mithilfe einer Farm Kachel auf dem Spielfeld.
 * Hauptbestandteil sind die Attribute und Methoden, die dazu dienen das Konzept im Spiel umzusetzen.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.4
 */
public class Farm extends Building {
    private static final Rectangle2D IMG_BG_VIEW = new Rectangle2D(96*2+32, 96*1+32, 64, 64);
    private static final Rectangle2D IMG_BD_VIEW = new Rectangle2D(96*7+32, 96*6+32, 64, 64);

    private CornTank silo;

    /**
     * Der Konstruktor initialisiert ein Objekt des Typ Farm.
     * Ein Farmobjekt ist nicht befahrbare Kachel auf dem Spielfeld und erhält mit den Parametern Werte für Größe und Kapazität des Silos.
     * @param FarmSiloCapacity definiert die Kapazität des Silos. So wird festgelegt, wie viel Getreide ein Spieler auf seiner Farm maximal lagern kann.
     */
    public Farm(double FarmSiloCapacity) {
        super(IMG_BD_VIEW, IMG_BG_VIEW, 3, 2);
        openToTraffic = false;
        silo = new CornTank(FarmSiloCapacity, 0);
    }

    /**
     * Getter der Silokapazität.
     * @return gibt die maximale Kapazität des Silos zurück.
     */
    public double getSiloCapacity() {
        return silo.getMaxCapacity();
    }

    /**
     * Getter des freien Platz des Silos.
     * @return gibt das verfügbare Volumen im Silo zurück.
     */
    public double getSiloLevel() {
        return silo.getCurrentLevel();
    }

    /**
     * Methode zum Füllen des Silos.
     * @param amount Betrag um den das Silo erhöht werden soll.
     */

    public void fillSilo(double amount) {
        silo.fill(amount);
    }

    /**
     * Methode zur Entnahme von Getreide aus dem Silo.
     * @param amount Betrag um den das Silo reduziert werden soll.
     */
    public void reduceSilo(double amount) {
        silo.reduce(amount);
    }
}
