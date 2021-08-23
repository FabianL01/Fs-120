package de.dhbw.fs120.tile;
/**
 * Diese Exception wird geworfen, wenn der Spieler versucht mit einem Feld zu interagieren
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1.2
 */
// ob das die richtige Exceptionklasse ist von der geerbt wird, ist mir noch nicht klar, vielleicht hat jemand ne bessere Idee?
public class fieldIsNotYourPropertyException extends IllegalCallerException{
    /**
     * Konstruktor mit Standardfehlermeldung.
     */
    public fieldIsNotYourPropertyException() {
        super("Dieses Feld gehört dir nicht!!!!");
    }

    /**
     * Konstruktor für spezifische Fehlermeldungen.
     * @param text Der Text der spezifischen Fehlermeldung.
     */
    public fieldIsNotYourPropertyException(String text) {
        super(text);
    }
}
