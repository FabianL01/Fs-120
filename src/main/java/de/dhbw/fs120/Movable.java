package de.dhbw.fs120;

import de.dhbw.fs120.game.Direction;
import de.dhbw.fs120.vehicle.Vehicle;

/**
 * Stellt Methoden für den Bewegungsablauf der beweglichen Spielobjekte innerhalb des Spielfeldes bereit.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1
 */
public interface Movable {
     /**
      * Das Objekt wird in die gewünschte Richtung bewegt.
      * Eine Bewegung ist nur möglich, wenn zuvor die Prüfung auf eine mögliche Kollision mithilfe der
      * {@link Movable#checkCollision} Methode mit dem Spielfeldrand oder einem nicht
      * betretbaren {@link de.dhbw.fs120.tile.Tile} negativ ausgefallen ist.
      * @param direction Die Richtung in welche sich das bewegliche Objekt bewegen soll (Nord, Ost, Süd, West).
      */
     public void move(Direction direction);

     /**
      * Es wird überprüft ob die geplante Bewegung eine Kollision zur Folge hätte.
      * Bewegungen über den Spielfeldrand hinaus oder über nicht betretbare {@link de.dhbw.fs120.tile.Tile}
      * Objekte soll nicht möglich sein.
      *
      * @return Ergebnis der Kollisionsprüfung (true - Kollision bzw. false - keine Kollision).
      */
     public boolean checkCollision();

     /**
      * Es wird überprüft ob eine Interaktion auf der aktuellen Position möglich ist.
      * Bewegliche Objekte können unterschiedliche Aktionen innerhalb des Spiels ausführen.
      * Ausgelöst durch die Betätigung der Interaktions-Taste durch den Nutzer, sollen mögliche Aktionen
      * mit {@link de.dhbw.fs120.tile.Field}, {@link de.dhbw.fs120.tile.Building} oder {@link Vehicle} gefunden werden.
      * Kann interagiert werden, wird ein entsprechendes Event ausgelöst.
      */
     public void checkInteraction();
}
