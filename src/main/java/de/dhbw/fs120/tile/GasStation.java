package de.dhbw.fs120.tile;

/**
 * Diese Klasse implementiert das Konzept einer Tankstelle und des Betankens von Fahrzeugen an dieser im Rahmen des Spiels.
 * Dies geschieht mithilfe einer GasStation Kachel auf dem Spielfeld.
 * Hauptbestandteil sind die Attribute und Methoden, die dazu dienen das Konzept im Spiel umzusetzen.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.5
 */
public class GasStation extends Building {
    /**
     * Benzinpreis
     */
    private double price;

    /**
     * Der Konstruktor initialisiert ein neues Objekt der Klasse GasStation.
     * @param sizeOfTile gibt die Größe der Kachel an.
     * @param startPrice setzt den ursprünglichen Preis von Benzin.
     * @param difficultyLevel übergibt dem Objekt die Schwiriegkeitstufe des Spiels.
     */
    public GasStation(int sizeOfTile, double startPrice, String difficultyLevel) {
        openToTraffic = false;
        tileSize = sizeOfTile;
        price = startPrice;
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Dieser Konstruktor wird benötigt wenn ein altes Spiel geladen werden soll
     * @param stringFromSavedField Das ist der String, der das gespecherte Feld beschreibt bzw. der gespeichert wurde
     */
    public GasStation(String stringFromSavedField) {
        String[] propertiesOfField = stringFromSavedField.split(",");

        openToTraffic = false;
        price = Double.parseDouble(propertiesOfField[0]);
        this.difficultyLevel = propertiesOfField[1];
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

    /**
     *
     * @return gibt den String zurück, der die Klasse beschreibt. Dieser wird beim speichern des Spiels gespeichert.
     */
    public String toString() {
        String stringForSaving = price+","+difficultyLevel;
        return stringForSaving;
    }

}
