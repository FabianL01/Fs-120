package de.dhbw.fs120.tile;

import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * Diese Klasse implementiert das abstrakte Konzept einer Kachel.
 * Das Spielfeld besteht aus Kacheln verschiedener Arten, die alle von dieser Klasse Tile erben.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.3
 */
public abstract class Tile {

    /**
     * Die Schwierigkeitsstufe des Spiels
     */
    protected String difficultyLevel;

    /**
     * Die Größe der Kachel
     * */
    protected int tileSize;

    /**
     * Definiert die Befahrbarkeit der Kachel
     */
    protected boolean openToTraffic;  //kann die Kachel befahren werden?

    //Zeitmodell aus 12 Zeiteinheiten (Monaten), dies ist Notwendig, um die Zeitbasierten parts möglchstlogisch und schön durchzuführen
    //es verinfacht das auch, da nun lediglich in der Klasse Game definiert werden muss nach wie viel Sekunden/Minuten eine Zeiteinheit
    // (Monat) verstreicht.... von diesem Modell ist dieMöglcihkeit der Ernte und der Saat abhängig, sowie auch der getreidepreis (dynamisch)
    protected int month;

    /**
     * Getter für das Atrribut openToTraffic, das beschreibt ob die Kachel durchquerbar ist.
     * @return den Wert des Attributs openToTraffic
     */
    public boolean isOpenToTraffic() {
        return openToTraffic;
    }

    /**
     * Getter für das Attribut month, das beschreibt welcher Monat in der Kachel ist.
     * @return den Wert des Attributs month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter für das Attribut tileSize, die Größe der Kachel.
     * @return den Wert des Attributs tileSize.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Setter für das Attribut month, das den Monat in der Kachel beschreibt.
     */
    public void updateMonth() {
        if (month < 12) {
            month++;
        }
        else{
            month = 1;
        }
    }

    // kann auch sein, dass das unnötig ist weil es eine methode in irgendeiner Standardklasse gibt, die das bereits tut
    // allerdings weiss ich das nciht und habe es deshalb erstmal selber gemacht um witer coden zu können
    // die Notwendigkeit dieser Methode ist also nciht geklärt

    /**
     * Diese Methode erlaubt die Erzeugung einer zuälligen Zahl in einem Intervall zwischen minValue and maxValue mit definiert vielen Stellen.
     * @param minValue minimaler Wert des Intervalls.
     * @param maxValue maximaler Wert des Intervalls.

     * @return eine zufällige Zahl, die im spezifizierten Intervall liegt.
     */


    protected double randomNumber( double minValue, double maxValue) {
        double number;
        Random random = new Random();

        DoubleStream numbers = random.doubles(1,minValue,maxValue);
        return Math.round(numbers.sum()*100)/100;
    }
}
