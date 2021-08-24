package de.dhbw.fs120.vehicle;

import de.dhbw.fs120.CapacityMismatchException;

/**
 * Tranktor mit dem sich der Spieler auf dem Spielfeld bewegen kann. Bei der Bewegung verbraucht der Traktor Treibstoff
 * aus seinem Treibstofftank. Der Traktor kann unterschiedliche {@link Attachable} Objekte an sich anhängen, um diese
 * auf dem Spielfeld zu bewegen.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class Tractor extends Vehicle implements Towing {
    /**
     * Maximale Kapazität des Treibstofftanks.
     */
    public static final double GAS_CAPACITY = 200;
    /**
     * Durchschnittlicher Verbrauch pro Bewegungseinheit.
     */
    public static final double GAS_CONSUMPTION = 1.5;
    /**
     * Tank für den Treibstoff.
     */
    private GasTank gasTank;

    /**
     * Bei der Erzeugung von einem neuen Spiel wird der Treibstofftank standardmäßig voll befüllt.
     */
    public Tractor(){
        gasTank = new GasTank(GAS_CAPACITY, GAS_CAPACITY);
    }

    /**
     * Wenn ein vorhandener Spielstand geladen wird, wird der Traktor mit dem alten Tankstand initialisiert.
     * @param gasLevel gibt den alte Tankstand aus der Save Datei mit.
     */
    public Tractor(String gasLevel){
        Double gasTankLevel = 0d;
        try {
            gasTankLevel = Double.parseDouble(gasLevel);
        } catch (NumberFormatException e){
            System.out.println(e);
        }
        gasTank = new GasTank(GAS_CAPACITY, gasTankLevel);
    }

    @Override
    public void move(String direction) {
        try {
            gasTank.reduce(GAS_CONSUMPTION);
        } catch(CapacityMismatchException e){

        }
    }

    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public void checkInteraction() {

    }

    @Override
    public void attach() {

    }

    /**
     * Die toString Methode des Traktors liefert den Tankstand, so das dieser gespeichert und später geladen werden kann.
     * @return liefert den Tankstand als String für eine Verwendung in der Speicherdatei zurück.
     */
    public String toString(){
        return (String.valueOf(this.gasTank.getCurrentLevel()));
    }

}
