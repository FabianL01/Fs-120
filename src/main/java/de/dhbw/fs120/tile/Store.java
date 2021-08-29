package de.dhbw.fs120.tile;

import de.dhbw.fs120.game.DifficultyLevel;
import javafx.geometry.Rectangle2D;

/**
 * Diese Klasse implementiert das Konzept eines Ladens und des Verkaufen von Getreide im Laden.
 * Dies geschieht mithilfe einer Store Kachel auf dem Spielfeld.
 * @author Lena Hammerer, Nick Hillebrand, Fabian Lulikat
 * @version 0.1.5
 */
public class Store extends Building {
    /**
     * Der Bildausschnitt für die grafische Darstellung des Grundstückes.
     */
    private static final Rectangle2D IMG_BG_VIEW = new Rectangle2D(96*2+32, 96*1+32, 64, 64);
    /**
     * Der Bildausschnitt für die grafische Darstellung des Ladens.
     */
    private static final Rectangle2D IMG_BD_VIEW = new Rectangle2D(96*15+32, 96*2+32, 64, 64);

    /**
     * Getreidepreis
     */
    private double cornPrice;

    private int [] [] intervalsOfMonths;

    /**
     * Der Konstruktor erzeugt ein neues Objekt der Klasse Store.
     * Dabei werden die folgenden Parameter übergeben:
     * @param difficultyLevel die Schwiriegkeitstufe des Spiels.
     */
    public Store(DifficultyLevel difficultyLevel, int month) {
        super(IMG_BD_VIEW, IMG_BG_VIEW, 2, 2, "Landhalle");
        openToTraffic = false;
        this.month = month;                                  // Spiel startet im August oder????? setter wäre hier unnötig, nur update macht Sinn
        this.difficultyLevel = difficultyLevel;

        int[][] intervals = {{290, 330}, {310, 360}, {290, 330}, {260, 300}, {230, 270}, {210, 250}, {180, 230}, {160, 220}, {180, 230}, {210, 250}, {230, 270}, {260, 300}};
        intervalsOfMonths = intervals;
    }

    /**
     * Dieser Konstruktor wird benötigt wenn ein altes Spiel geladen werden soll
     * @param stringFromSavedField Das ist der String, der das gespecherte Feld beschreibt bzw. der gespeichert wurde
     */
    public Store(String stringFromSavedField) {
        super(IMG_BD_VIEW, IMG_BG_VIEW, 2, 2, "Landhalle");
        String[] propertiesOfField = stringFromSavedField.split(",");

        openToTraffic = false;
        this.month = Integer.parseInt(propertiesOfField[2]);                                  // Spiel startet im August oder????? setter wäre hier unnötig, nur update macht Sinn
        this.difficultyLevel = DifficultyLevel.valueOf(propertiesOfField[1]);
        cornPrice = Double.parseDouble(propertiesOfField[0]);

        int[][] intervals = {{290, 330}, {310, 360}, {290, 330}, {260, 300}, {230, 270}, {210, 250}, {180, 230}, {160, 220}, {180, 230}, {210, 250}, {230, 270}, {260, 300}};
        intervalsOfMonths = intervals;
    }

    /**
     * Diese Methode implementiert den Verkauf von Getreide im Spiel.
     * Anhand des Parameters cornToSale und dem derzeitigen Preis für Getreide wird der Gesamtpreis ermittelt.
     * @param cornToSale gibt an wie viel Getreide verkauft wird.
     * @return den Gesamtpreis für das verkaufte Getreide.
     */
    public double sellCorn(double cornToSale) {

        return cornToSale*cornPrice;
    }

    public double getCornPrice() {
        return cornPrice;
    }

    // Die Idee ist, dass man das Modell mit den Monaten ( es ist nur ein Modell, welches die Komplexität nicht erhöht aber eine
    // schöne Steigerung der Wachstumsstufen in Field ermöglicht!!) nutzt, um den Getreidepreis dynamisch zu gestalten

    /**
     * Diese Methode erzeugt einen dynamischen Preis für Getreide.
     * Anhand der Schwierigkeitsstufe und und dem derzeitigen Monat wird ein gewisser Preis erzeugt.
     * Die Methode nutzt dafür ein zweidimensionales Array.
     */
    public void setCurrentPrice() {
        if(difficultyLevel.equals("mittel")||difficultyLevel.equals("schwer")) {
            cornPrice = randomNumber(intervalsOfMonths[month-1][0], intervalsOfMonths[month-1][1]);
        }
        else {
            cornPrice = 300;    // default Preis könnte beim Spielmodus "leicht" zum Tragen kommen
        }
    }

    /**
     * Update Methode wird überschrieben, jedesmal wenn der Monat geupdatet wird, wird ein neuer corn Preis
     * festgelegt, dieser wird durch eine Zufallszahlbestimmt. Damit der User jetzt aber nciht mehrmals in einem
     * Monat einen neuen Preis anfordern kann ( man verhindert hier also den Missbracuh dieser Funktionalität durch
     * den User), um so den bestmöglichen Monatspreis zu bekommen. Das bedeutet konkret, dass die Preisdynamik
     * jedes "Jahr" gleich bleibt, aber die Preis in jedem ahr leicht unterschiedlich sind, der Preis wird in jedem Monat
     * für den Monat bestimmt.
     */
    public void updateMonth() {
        if (month < 12) {
            month++;
        }
        else{
            month = 1;
        }
        setCurrentPrice();
    }

    /**
     *
     * @return gibt den String zurück, der die Klasse beschreibt. Dieser wird beim speichern des Spiels gespeichert.
     */
    public String toString() {
        String stringForSaving = cornPrice+","+difficultyLevel+","+month;
        return stringForSaving;
    }
}
