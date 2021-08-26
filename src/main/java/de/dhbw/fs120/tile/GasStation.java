package de.dhbw.fs120.tile;

import de.dhbw.fs120.game.DifficultyLevel;
import javafx.geometry.Rectangle2D;

/**
 * Diese Klasse implementiert das Konzept einer Tankstelle und des Betankens von Fahrzeugen an dieser im Rahmen des Spiels.
 * Dies geschieht mithilfe einer GasStation Kachel auf dem Spielfeld.
 * Hauptbestandteil sind die Attribute und Methoden, die dazu dienen das Konzept im Spiel umzusetzen.
 * @author Lena Hammerer, Nick Hillebrand, Fabian Lulikat
 * @version 0.1.4
 */
public class GasStation extends Building {
    /**
     * Der Bildausschnitt für die grafische Darstellung des Grundstückes.
     */
    private static final Rectangle2D IMG_BG_VIEW = new Rectangle2D(96*2+32, 96*1+32, 64, 64);
    /**
     * Der Bildausschnitt für die grafische Darstellung der Tankstelle.
     */
    private static final Rectangle2D IMG_BD_VIEW = new Rectangle2D(96*8+32, 96*6+32, 64, 64);
    /**
     * Benzinpreis
     */
    private double price;

    /**
     * Der Konstruktor initialisiert ein neues Objekt der Klasse GasStation.
     * @param startPrice setzt den ursprünglichen Preis von Benzin.
     * @param difficultyLevel übergibt dem Objekt die Schwiriegkeitstufe des Spiels.
     */
    public GasStation(double startPrice, DifficultyLevel difficultyLevel) {
        super(IMG_BD_VIEW, IMG_BG_VIEW, 2, 2, "Tankstelle");
        openToTraffic = false;
        price = startPrice;
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Getter für den Benzinpreis
     * @return gibt den Preis des Benzins.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Diese Methode implementiert den Kaufprozess von Benzin.
     * Basierend auf der Menge an zu tankendem Benzin und dem Schwiriegkeitsgrad wird ein zu zahlender Preis ermittelt.
     * @param litersOfGas übergibt der Funktion die vom Spieler gewollte Menge Benzin.
     * @return den Betrag, den der Spieler zahlen soll.
     */
    public double buyGas(double litersOfGas) {
        double moneyToPay;

        if(difficultyLevel.equals("leicht")){
            moneyToPay = litersOfGas*price;
        }
        else {
            moneyToPay = litersOfGas*price;

            if(difficultyLevel.equals("mittel")) {
                price = randomNumber(1, 2);
            }
            else {
                price = randomNumber(1,3);
            }
        }

        return moneyToPay;
    }
}
