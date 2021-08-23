package de.dhbw.fs120.vehicle;

/**
 * Grundfunktion die benötigt wird, um an ein Fahrzeug angehängt zu werden.
 * Als Basis dient die Klasse {@link Vehicle} da ein angehängter Anhänger, temporär als eigenes Fahrzeug betrachtet wird.
 * Zudem werden der {@link Tractor} und der {@link de.dhbw.fs120.game.Player} temporär durch den Anhänger ersetzt.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public abstract class Attachable extends Vehicle{

    /**
     * Da ein angehängter Anhänger temporär als eigenes Fahrzeug betrachtet wird und den {@link de.dhbw.fs120.game.Player},
     * sowie den {@link Tractor} ersetzt, muss die Methode für das Aussteigen so angepasst werden,
     * dass der Anhänger abgekoppelt und Fahrzeug und Anhänger geparkt werden.
     */
    @Override
    public void exit(){};
}
