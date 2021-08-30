package de.dhbw.fs120.game;

import de.dhbw.fs120.Movable;
import de.dhbw.fs120.tile.Tile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Spieler, der sich auf dem Spielfeld bewegen und mit den unterschiedlichen Spielobjekten interagieren kann.
 *
 * @author Lena Hammerer, Jonas Zagst
 * @version 0.1.2
 */
public class Player extends Pane implements Movable {
    /**
     * Dieses Bild wird als Spritesheet für den Spieler genutzt.
     */
    protected static final Image SPRITESHEET = new Image(String.valueOf(Player.class.getResource("/img/player_spritesheet.png")));
    /**
    * Der Bildausschnitte für die grafische Darstellung des Spielers.
    */
    private static final Rectangle2D[] IMG_VIEWS = {
            new Rectangle2D(12, 10, 64, 135),
            new Rectangle2D(12, 150, 64, 135),
            new Rectangle2D(12, 288, 64, 135),
            new Rectangle2D(12, 425, 64, 135)};
    /**
     * Ein Bild für die grafische Darstellung des Spielers.
     */
    protected ImageView imageView = new ImageView();
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
        imageView.setImage(SPRITESHEET);
        imageView.setViewport(IMG_VIEWS[0]);
        imageView.setFitHeight(32);  // Standardgröße 32
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        this.getChildren().add(imageView);
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
        imageView.relocate(gridColumn*32 + 8, gridRow*32);
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

    /**
     * Diese Methode reduziert den Kontostand des Spielers um einen bestimmten Betrag, sofern das Konto entsprechend
     * gedeckt ist.
     * @param amount Betrag, um den der Kontostand des Spielers reduziert werden soll.
     * @throws NotEnoughMoneyException Fehler, falls Kontostand den Betrag nicht abdeckt.
     */
    public void spendMoney(double amount) throws NotEnoughMoneyException {
        if(money.getValue() - amount >= 0) {
            money.setValue(money.getValue() - amount);
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    /**
     * Mit dieser Methode kann der Spieler auf dem Spielfeld in eine bestimmte Richtung bewegt werden.
     * Die grafische Darstellung wird entsprechend angepasst.
     * @param direction Die Richtung in welche sich das bewegliche Objekt bewegen soll (Nord, Ost, Süd, West).
     */
    @Override
    public void move(Direction direction) {
        // Kollisionscheck wird aktuell in Game vor der Bewegung durchgeführt
        switch (direction) {
            case NORTH:
                imageView.setTranslateY(imageView.getTranslateY() - 32);
                imageView.setViewport(IMG_VIEWS[2]);
                gridRow--;
                break;
            case SOUTH:
                imageView.setTranslateY(imageView.getTranslateY() + 32);
                imageView.setViewport(IMG_VIEWS[0]);
                gridRow++;
                break;
            case WEST:
                imageView.setTranslateX(imageView.getTranslateX() - 32);
                imageView.setViewport(IMG_VIEWS[3]);
                gridColumn--;
                break;
            case EAST:
                imageView.setTranslateX(imageView.getTranslateX() + 32);
                imageView.setViewport(IMG_VIEWS[1]);
                gridColumn++;
                break;
        }
    }

    @Override
    public boolean checkCollision(){ return false; }

    // Möglichkeit der Prüfung über die Bounds der Objekte anstatt der Grid-Position
    // wird aktuell nicht genutzt, könnte noch nützlich sein
    public boolean checkCollisionTile(Tile t) {
        Bounds playerBounds = imageView.localToScene(imageView.getBoundsInLocal());
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
