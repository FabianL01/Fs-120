package de.dhbw.fs120.tile;

import de.dhbw.fs120.game.DifficultyLevel;
import javafx.geometry.Rectangle2D;

/**
 * Diese Klasse implementiert das Konzept eines Getreidefelds und seiner Bewirtschaftung im Rahmen des Spiels.
 * Dies geschieht mithilfe von Field Kacheln auf dem Spielfeld.
 * Hauptbestandteil sind die Attribute und Methoden, die dazu dienen ein Feld im Spiel darzustellen und zu manipulieren.
 * @author Lena Hammerer, Nick Hillebrand, Fabian Lulikat
 * @version 0.1.5
 */
public class Field extends Tile {
    /**
     * Die Bildausschnitte für die grafische Darstellung des Feldes.
     */
    private static final Rectangle2D[] IMG_VIEW = { // aktuell noch immer gleich
            new Rectangle2D(96*2+32, 96*6+32, 64, 64),
            new Rectangle2D(96*2+32, 96*6+32, 64, 64),
            new Rectangle2D(96*2+32, 96*6+32, 64, 64),
            new Rectangle2D(96*2+32, 96*6+32, 64, 64),
            new Rectangle2D(96*2+32, 96*6+32, 64, 64)};

    /**
     * Preis des Feldes
     */
    private double fieldPrice;

    /**
     * Status des Feldes (0-4 Wachstumsstufen; -1 = nicht im Besitz des Spielers)
     */
    private int status;

    /**
     * Der Konstruktor initialisiert ein neues Objekt des Typ Feld.
     * Jedes Feld ist befahrbar, enthält keine Ernteerträge, hat einen definierten Kaufpreis
     * und befindet sich nicht im Besitz des Spielers, was durch den Status -1 repräsentiert wird.
     */
    public Field(DifficultyLevel difficultyLevel, double fieldPrice) {
        super(IMG_VIEW[0]);
        openToTraffic = true;
        status = -1;                // bei -1 befindet sich das Feld noch nicht im Besitz des Players.......
        this.fieldPrice = fieldPrice;
        this.difficultyLevel = difficultyLevel;
                            // Es muss noch überlegt werden wie viel ein Feld kostet ( sollte auch davon abhängig sein wie viel die Ernte einbringt und wie teuer der Sprit ist)
      }

    /**
     * Dieser Konstruktor wird benötigt wenn ein altes Spiel geladen werden soll
     * @param stringFromSavedField Das ist der String, der das gespecherte Feld beschreibt bzw. der gespeichert wurde
     */
      public Field(String stringFromSavedField) {
          super(IMG_VIEW[0]);
          String[] propertiesOfField = stringFromSavedField.split(",");

          int statusFromSavedField = Integer.parseInt(propertiesOfField[0]);
          String difficultyLevelFromSavedField = propertiesOfField[1];
          double fieldPriceOfSavedField = Double.parseDouble(propertiesOfField[2]);
          int monthOfSavedField = Integer.parseInt(propertiesOfField[3]);


          openToTraffic = true;
          status = statusFromSavedField;                // bei -1 befindet sich das Feld noch nicht im Besitz des Players.......
          this.fieldPrice = fieldPrice;
          this.difficultyLevel = DifficultyLevel.valueOf(difficultyLevelFromSavedField);
          month = monthOfSavedField;
      }

    /**
     * Getter des Status des Felds.
     * @return den Status des Felds. 0-4 beschreiben den Wachstumsprozess. -1 repräsentiert ein ungekauftes Feld.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Getter für den Feldpreis.
     * @return der Preis des Feldes.
     */
    public double getFieldPrice() {
        return fieldPrice;
    }

    /**
     * Diese Methode implementiert den Erwerb eines Feldes.
     * Dafür wird der Status des Feldes verändert und anschließend der Kaufpreis zurückgegeben.
     * @param numberOfFieldsAlreadyOwnedByUser
     * @return den ermittelten Kaufpreis.
     * @throws fieldAlreadySoldException, falls sich das Feld schon im Besitz des Spielers befindet.
     */
    public double  buyField(int numberOfFieldsAlreadyOwnedByUser) throws fieldAlreadySoldException {
        setFieldPrice(numberOfFieldsAlreadyOwnedByUser);
        updateStatus();
        return getFieldPrice();
    }



    /**
     * Diese Methode ermittelt den Kaufpreis eines Feldes, besitzt der Spieler mehr als 5 Felder, dann steigt der Kaufpreis weiterer Felder.
     * Funktionsweise: // Die Idee ist hier, dass das zusätzliche Kaufen neuer Felder mit steigenden Feldern im Besitz teuerer wird,
                 *     // da das Spiel sonst zu schnell durchgespielt wäre (steigender Gewinn des players bei mehr Feldern würde hier
                 *     // ansonsten dafür sorgen, dass der Kauf neuer Felder immer schneller möglich wird )
     * @param numberOfFields gibt die Anzahl der Felder im Besitz des Spielers.
     * @return den Kaufpreis des Feldes
     * @throws fieldAlreadySoldException
     */
    public void setFieldPrice(int numberOfFields) throws fieldAlreadySoldException{
            if( status == -1 && numberOfFields >5 ) {
                fieldPrice = fieldPrice+50000;
            }
            else if (status != -1){
                throw new fieldAlreadySoldException("Das Feld gehört dir schon!");
            }
    }

    // ernten kann man nur wenn das Getreide reif ist und wenn einem das Feld gehört
    // die Erntemenge ist zufällig und bewegt sich je nach Schwierigkeitsgrad in einem bestimmten Intervall
    // auch das war nur eine Idee von mir und muss nicht so belassen werden (allerdings verursacht das keine Steigerung der Komplexität bei der weiteren Umsetzung des Spiels)

    /**
     * Diese Methode implementiert den Erntevorgang im Spiel.
     * Abhängig vom Schwierigkeitsgrad und wird eine gewisse Menge Getreide als Ernteertrag gesetzt.
     * Funktionsweise:// ernten kann man nur wenn das Getreide reif ist und wenn einem das Feld gehört
     *                // die Erntemenge ist zufällig und bewegt sich je nach Schwierigkeitsgrad in einem bestimmten Interval.
     * @throws fieldNotReadyForActionException, falls das Feld nicht bewirtschaftet werden kann.
     * @throws fieldIsNotYourPropertyException, falls das Feld dem Spieler nicht gehört.
     */
    public double harvest() throws fieldIsNotYourPropertyException, fieldNotReadyForActionException {
        double harvest = 0;

        if( status == 4 ) {
            if(difficultyLevel.equals("leicht")){
                harvest = randomNumber(60, 90);
            }
            else if (difficultyLevel.equals("mittel")) {
                harvest = randomNumber(40, 60);
            }
            else if (difficultyLevel.equals("schwer")) {
                harvest = randomNumber(15, 40);
            }
            updateStatus();
            return harvest;
        }
        else if(status == -1) {
            throw new fieldIsNotYourPropertyException();
        }
        else {
            throw new fieldNotReadyForActionException("Das Getreide ist noch nicht Reif! Du musst noch etwas warten.");
        }
    }

    // Grubbern soll man immer dürfen es sei denn das Feld gehört einem nicht
    // auch hier kann man weiter Einschränkungen hinzufügen wenn wir uns dazu entscheiden sollten

    /**
     * Diese Methode implementiert den Vorgang der Saatvorbereitung.
     * @throws fieldIsNotYourPropertyException
     * @throws fieldNotReadyForActionException
     * @link Grubber aus vehicles korrekt einfügen!!!
     */
    public void prepareForSeed() throws fieldIsNotYourPropertyException, fieldNotReadyForActionException {
        if(status != -1 && status == 5) {
            status = 0;
        }
        else if(status == -1) {
            throw new fieldIsNotYourPropertyException();
        }
        else {
            throw new fieldNotReadyForActionException("Du kannst dieses Feld noch nicht Grubern!!");
        }
    }

    // die Überlegung war, dass man nur aussäen kann, wenn die Zeit stimmt
    //die Bedingung und die zugehörige Exception kann man natürlich aus raus lassen, war nur ne Idee meinerseits

    /**
     * Diese Methode implementiert den Vorgang des Aussähens auf einem Feld.
     * @throws fieldNotReadyForActionException, falls das Feld nicht bewirtschaftet werden kann.
     * @throws fieldIsNotYourPropertyException, falls das Feld dem Spieler nicht gehört.
     */
    public void seed() throws fieldNotReadyForActionException, fieldIsNotYourPropertyException {
        if(status == 0 && (month == 8 || month == 7 )) {
            updateStatus();
        }
        else if(status == -1) {
            throw new fieldIsNotYourPropertyException();
        }
        else{
           throw new fieldNotReadyForActionException();
        }
    }

    private void updateStatus() {
        if (status < 5) {
            status++;
        }
        else{
            status = 0;
        }
    }

    // diese Methode wurde schon in Tile implementiert um die Zeit vielleicht auch beim Getreidepreis zu berücksichtigen (siehe Klasse Store)
    // hier wird diese Methode überschrieben, weil im Fall von Field beim updaten des Monats eventuell auch der wachstumsstatus
    // geupdatet werden soll

    /**
     * Diese Methode überschreibt die gleichnamige Methode in Tile, damit der Wachstumstatus des Feldes berücksichtigt wird.
     */
    public void updateMonth() {
        if (month < 12) {
            month++;
        }
        else{
            month = 1;
        }

        if(status != 0 && status != -1 && status != 4 && status != 5) {
            if(month > 9 || month < 4)  {
                updateStatus();
            }
            else if(month >= 4 || month < 7) {
                updateStatus();
            }
            else if(month == 7) {
                updateStatus();
            }
        }
    }

    /**
     *
     * @return gibt den String zurück, der die Klasse beschreibt. Dieser wird beim speichern des Spiels gespeichert.
     */
    public String toString() {
        String stringForSaving = status+","+difficultyLevel+","+fieldPrice+","+month;
        return stringForSaving;
    }
}
