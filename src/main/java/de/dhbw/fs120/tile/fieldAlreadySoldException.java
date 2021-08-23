package de.dhbw.fs120.tile;
/**
 * Diese Exception wird geworfen, wenn der Spieler versucht ein Feld zu kaufen, das er bereits besitzt.
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.2
 */
// ob das die richtige Exceptionklasse ist von der geerbt wird, ist mir noch nicht klar, vielleicht hat jemand ne bessere Idee?
public class fieldAlreadySoldException extends IllegalArgumentException {

    /**
     * Konstruktor mit Standardfehlermeldung.
     */
    public fieldAlreadySoldException(){super("Du hast dieses Feld schon gekauft!");}

    /**
     * Konstruktor für spezifische Fehlermeldungen.
     * @param text Der Text der spezifischen Fehlermeldung.
     */
    public fieldAlreadySoldException(String text) {
        super(text);
    }
}
