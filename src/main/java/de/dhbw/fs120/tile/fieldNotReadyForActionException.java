package de.dhbw.fs120.tile;

/**
 * Diese Exception wird geworfen, falls der Spieler eine unerlaubte Aktion an einem Feld ausführen möchte.
 * @author NIck Hillebrand, Fabian Lulikat
 * @version 0.1.2
 */

public class fieldNotReadyForActionException extends IllegalCallerException {

    /**
     * Konstruktor mit Standardfehlermeldung.
     */
    public fieldNotReadyForActionException() {
        super("Das Feld ist noch nicht bereit!");
    }

    /**
     * Konstruktor für spezifische Fehlermeldungen.
     * @param text Der Text der spezifischen Fehlermeldung.
     */
    public fieldNotReadyForActionException(String text) {
        super(text);
    }
}
