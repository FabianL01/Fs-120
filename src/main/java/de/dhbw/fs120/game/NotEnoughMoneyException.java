package de.dhbw.fs120.game;
/**
 * Diese Exception wird geworfen, wenn der Spieler versucht etwas zu kaufen, für das er nicht genug Geld hat.
 * @author Lena Hammerer
 * @version 0.1
 */

public class NotEnoughMoneyException extends IllegalArgumentException{
    /**
     * Konstruktor mit Standardfehlermeldung.
     */
    public NotEnoughMoneyException() {
        super("Du hast nicht genug Geld für diese Aktion!");
    }

    /**
     * Konstruktor für spezifische Fehlermeldungen.
     * @param text Der Text der spezifischen Fehlermeldung.
     */
    public NotEnoughMoneyException(String text) {
        super(text);
    }
}
