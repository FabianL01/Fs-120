package de.dhbw.fs120.game;

import de.dhbw.fs120.Movable;
import de.dhbw.fs120.tile.Tile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Spieler, der sich auf dem Spielfeld bewegen und mit den unterschiedlichen Spielobjekten interagieren kann.
 *
 * @author Lena Hammerer, Jonas Zagst
 * @version 0.1.2
 */
public class Player extends Pane implements Movable {
    // Wird sobald Asset vorhanden mit ImageView ersetzt
    private Rectangle playerRec = new Rectangle(16, 16, Color.RED);
    /**
     * Die aktuelle Spaltenposition des Spielers auf dem 30x20 Raster des Spielfeldes.
     */
    private int gridColumn;
    /**
     * Die aktuelle Zeilenposition des Spielers auf dem 30x20 Raster des Spielfeldes.
     */
    private int gridRow;
    /**
     * Der Name des Spielers. Kann beim Spielstart festgelegt werden.
     */
    private String name;
    /**
     * Der aktuelle Kontostand des Spielers. Wird durch Käufe/Verkäufe verändert.
     */
    public DoubleProperty money = new SimpleDoubleProperty();

    /**
     * Konstruktor zur Erzeugung eines Spielers ohne initiale Daten.
     */
    public Player(){
        this.getChildren().add(playerRec);
    }

    /**
     * Diese Methode wird aufgerufen, sobald die Spieldaten aus der Datei zur Verfügung stehen und dem Spieler zugewiesen
     * werden können.
     * @param name Der Name des Spielers
     * @param money Der aktuelle Kontostand des Spielers
     * @param x Die aktuelle Spaltenposition auf dem Raster
     * @param y Die aktuelle Zeilenposition auf dem Raster
     */
    public void loadPlayerData(String name, double money, int x, int y){
        this.name = name;
        this.money.set(money);
        gridColumn = x;
        gridRow = y;
        // Spieler in der Mitte der angegebenen Kachel platzieren
        playerRec.relocate(gridColumn*32 + playerRec.getWidth()/2, gridRow*32 + playerRec.getHeight()/2);
    }

    /**
     * Getter für die aktuelle Spaltenposition auf dem Spielfeld.
     * @return Die aktuelle Spaltenposition auf dem Spielfeld.
     */
    public int getGridColumn() {
        return gridColumn;
    }

    /**
     * Getter für die aktuelle Zeilenposition auf dem Spielfeld.
     * @return Die aktuelle Zeilenposition auf dem Spielfeld.
     */
    public int getGridRow() {
        return gridRow;
    }

    /**
     * Getter für den Namen des Spielers.
     * @return Der Name des Spielers.
     */
    public String getName() {
        return name;
    }

    // Zur Testzwecken der Steuerhinterziehung - to be removed
    public void makeRich(){
        this.money.set(1000000000d);
    }

    /**
     * Mit dieser Methode kann der Spieler auf dem Spielfeld in eine bestimmte Richtung bewegt werden.
     * @param direction Die Richtung in welche sich das bewegliche Objekt bewegen soll (Nord, Ost, Süd, West).
     */
    @Override
    public void move(Direction direction) {
        // Kollisionscheck wird aktuell in Game vor der Bewegung durchgeführt
        switch (direction) {
            case NORTH:
                playerRec.setTranslateY(playerRec.getTranslateY() - 32);
                gridRow--;
                break;
            case SOUTH:
                playerRec.setTranslateY(playerRec.getTranslateY() + 32);
                gridRow++;
                break;
            case WEST:
                playerRec.setTranslateX(playerRec.getTranslateX() - 32);
                gridColumn--;
                break;
            case EAST:
                playerRec.setTranslateX(playerRec.getTranslateX() + 32);
                gridColumn++;
                break;
        }
    }

    @Override
    public boolean checkCollision(){ return false; }

    // Möglichkeit der Prüfung über die Bounds der Objekte anstatt der Grid-Position
    // wird aktuell nicht genutzt, könnte noch nützlich sein
    public boolean checkCollisionTile(Tile t) {
        Bounds playerBounds = playerRec.localToScene(playerRec.getBoundsInLocal());
        Bounds tileBounds = t.localToScene(t.getBoundsInLocal());
        if(playerBounds.intersects(tileBounds)){
            System.out.println("Collision!");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void checkInteraction() {

    }
}
