package de.dhbw.fs120;

/**
 * Wird bei dem Fall ausgelöst, dass der Füllstand eines Tanks ungültig verändert werden soll.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public class CapacityMismatchException extends IllegalArgumentException{
    /**
     * Exception mit standardisierter Nachricht.
     */
    public CapacityMismatchException(){
        super("Aktion überschreitet die Kapazität oder den Füllstand.");
    }

    /**
     * Exception mit einer personalisierten Nachricht.
     * @param message Nachricht mit ergänzenden Informationen zum Vorfall.
     */
    public CapacityMismatchException(String message){
        super(message);
    }
}
