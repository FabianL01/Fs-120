package de.dhbw.fs120;

/**
 * Ein Tank zur Aufbewahrung unterschiedlicher Ressourcen (Treibstoff, Getreide).
 * Der Tank besitzt eine maximale Kapazität und kann nach Bedarf befüllt oder entleert werden.
 *
 * @author Lena Hammerer
 * @version 0.1
 */
public abstract class Tank {

    /**
     * Maximale Kapazität, die der Tank von der Ressource aufnehmen kann.
     */
    protected double maxCapacity;
    /**
     * Aktueller Füllstand des Tanks.
     */
    protected double currentLevel;

    /**
     * Bei der Erzeugung eines neuen Tanks müssen die maximale Kapazität sowie der initiale Füllstand festgelegt werden.
     * Die Kapazität kann zu einem späteren Zeitpunkt nicht mehr verändert werden. Der aktuelle Füllstand wird über entsprechende Methoden
     * im Laufe des Spiels nach Bedarf angepasst.
     *
     * @param maxCapacity Maximale Kapazität die der Tank von der Ressource aufnehmen kann.
     * @param initialLevel Füllstand bei der Initialisierung eines neuen Tanks.
     */
    public Tank(double maxCapacity, double initialLevel){
        this.maxCapacity = maxCapacity;
        this.currentLevel = initialLevel;
    }

    /**
     * Gibt die maximale Kapazität des Tanks zurück.
     * @return Maximalie Kapazität die der Tank von der Ressource aufnehmen kann.
     */
    public double getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Gibt den aktuellen Füllstand des Tanks zurück.
     * @return Aktueller Füllstand des Tanks.
     */
    public double getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Auffüllen des Tanks um eine bestimmte Menge. Es wird vor der Anpassung des aktuellen Füllstandes überprüft
     * ob die Füllung die maximale Kapazität des Tanks überschreiten würde.
     *
     * @param amount Menge um die der aktuelle Füllstand erhöht werden soll.
     * @throws CapacityMismatchException Bei einer Überschreitung der Kapazität wird eine Exception ausgelöst.
     */
    public void fill (double amount) throws CapacityMismatchException{
        if((currentLevel + amount) <= maxCapacity){
            currentLevel += amount;
        } else{
            throw new CapacityMismatchException();
        }
    }

    /**
     * Reduzieren des Tanks um eine bestimmte Menge. Es wird vor der Anpassung des aktuellen Füllstandes überprüft
     * ob die Entnahme den aktuellen Füllstand des Tanks überschreiten würde.
     *
     * @param amount Menge um die der aktuelle Füllstand erniedrigt werden soll.
     * @throws CapacityMismatchException Bei einer Überschreitung des Füllstands wird eine Exception ausgelöst.
     */
    public void reduce (double amount) throws CapacityMismatchException{
        if(currentLevel >= amount){
            currentLevel -= amount;
        } else{
            throw new CapacityMismatchException();
        }
    }
}
