package de.dhbw.fs120.game;

import de.dhbw.fs120.tile.Field;
import de.dhbw.fs120.tile.Tile;
import de.dhbw.fs120.tile.fieldAlreadySoldException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * In dieser Klasse entsteht die für das Spiel hauptsächlich genutzte Scene. Diese wird nach dem Start eines neuen
 * Spiels oder nach dem Laden eines Spielstandes erzeugt. Der globale Spielmonat wird aus dieser Klasse gesteuert und
 * die einzelnen Spielelemente wie das Spielfeld und der Spieler kommen zusammen.
 * Das Spiel arbeitet mit einem eigenen Stylesheet zur Anpassung von globalen Style-Optionen (Schriftart usw.).
 * Auf dieser Ebenen findet ebenfalls das EventHandling der Nutzereingaben (z.B. Bewegung des Spielers über
 * Tastatureingabe) statt.
 *
 * @author Lena Hammerer
 * @author Jonas Zagst
 * @version 0.1.3
 */
public class Game {
    /**
     * Die Scene welche schlussendlich von der Stage geladen werden kann.
     */
    private Scene gameScene;
    // Wahrscheinlich könnte auch die gesamte Klasse von Scene ergeben und man regelt alles über this ..
    // TODO: Überprüfen was eher MVC entspricht: Erben von Scene oder Klasseneingenschaft Scene
    /**
     * Das Spielfeld, welches alle Kacheln, Gebäude und Fahrzeuge sowie als Text dargestellte Informationen für den
     * Spieler enthält.
     */
    private Map gameMap;
    /**
     * Der Spieler, welcher sich auf dem Spielfeld bewegen und Interaktionen ausführen kann.
     */
    private Player player;
    /**
     * Der globale Monat, welcher für das Zeitmanagement benötigt wird. Es handelt sich um eine Property, um
     * beispielsweise das Binding an das Monats-Label in der Info-Anzeige zu ermöglichen.
     */
    private IntegerProperty month = new SimpleIntegerProperty();
    /**
     * Die Standardgröße für die Tiles im Raster
     */
    public static final int TILE_SIZE = 32; // public damit der Wert nicht durch alle Konstruktoren der Weltgeschichte geschoben werden muss

    /**
     * Im Konstruktor werden alle notwendigen Game-Elemente instanziiert und an die Scene angefügt. Der Monatszyklus
     * wird gestartet und Event-Handling für die unterschiedlichen Eingabeoptionen des Benutzers festgelegt.
     */
    public Game() {
        // Starten der Timeline (1min. pro Monat)
        startMonthlyCycle(60);

        // Instanziierung Game-Elemente:

        // der Spieler wird zunächst leer instanziiert und erst innerhalb der gameMap mit den Daten aus der
        // Sicherungsdatei befüllt, da das EventHandling auf dieser Ebenen stattfinden soll, die Daten allerdings
        // erst im Rahmen der Erstellung der eigentlichen Map geladen werden .. Ist das gut?
        // TODO: Überprüfen Konzept der Beziehung zwischen Game, Map, Player bzgl. EventHandling
        player = new Player();
        // Die Map braucht den Pfad zur eigentlichen Datei, den globalen Monat und Spieler.
        gameMap = new Map("src/main/resources/json/TemplateGame.json", month, player);
        // aktuell ist eine Kachel 32x32 groß und wird im 30x20 Raster angeordnet.
        // TODO: Überprüfen ob statische Variablen für Rastergröße sinnvoll. Evtl. auch über Konstruktor, aber vermutlich schwierig, da die Tile Images eine fixe größe haben
        gameScene = new Scene(gameMap, TILE_SIZE*30, TILE_SIZE*20);
        // CSS Stylsheet zur Festlegung von globalen Styles, wie Schriftart, Schriftfarbe, Schriftgröße, usw.
        gameMap.getStylesheets().add(String.valueOf(getClass().getResource("/stylesheets/styles.css")));

        // Event-Handling:

        // Bei Tastatureingabe
        gameScene.setOnKeyPressed(keyEvent -> {
            // WASD für die Bewegung des Spielers und später der Fahrzeuge
            // I für die Interaktion mit Spielelementen
            switch (keyEvent.getCode()) {
                case W:
                    // nur Bewegen, wenn die nächste Kachel befahrbar ist!
                    if(gameMap.getTileAtPos(player.getGridColumn(), player.getGridRow()-1).isOpenToTraffic())
                        player.move(Direction.NORTH);
                    break;
                case S:
                    if(gameMap.getTileAtPos(player.getGridColumn(), player.getGridRow()+1).isOpenToTraffic())
                        player.move(Direction.SOUTH);
                    break;
                case A:
                    if(gameMap.getTileAtPos(player.getGridColumn()-1, player.getGridRow()).isOpenToTraffic())
                        player.move(Direction.WEST);
                    break;
                case D:
                    if(gameMap.getTileAtPos(player.getGridColumn()+1, player.getGridRow()).isOpenToTraffic())
                        player.move(Direction.EAST);
                    break;
                case I:
                    Tile currentTile = gameMap.getTileAtPos(player.getGridColumn(), player.getGridRow());
                    // Feld kaufen
                    if(currentTile instanceof Field){
                        try{
                            ((Field)currentTile).buyField(player, gameMap.getNumberOfOwnedFields());
                        } catch(fieldAlreadySoldException e){   // Feld wurde bereist gekauft
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Feld kaufen");
                            alert.setHeaderText(null);
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
                        } catch (NotEnoughMoneyException e){    // Der Spieler hat nicht genug Geld
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Feld kaufen");
                            alert.setHeaderText(null);
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
                        }
                    }
                    break;
            }
        });
    }

    /**
     * Es wird der Monat auf Anfang des fiktiven Jahreszyklus gesetzt und eine neue Timeline erstellt sowie gestartet.
     * Es kann durch einen Parameter entschieden werden, wie lange ein einzelner fiktiver Monat dauern soll.
     * @param seconds Sekunden die ein fiktiver Monat in realer Zeit dauern soll.
     */
    private void startMonthlyCycle(int seconds){
        month.setValue(1); // Beginn des Jahres ist Monat 1
        // JavaFX Timeline funktioniert in diesem Fall irgendwie besser als der Java Timer.
        Timeline timeline =
                new Timeline(new KeyFrame(Duration.millis(seconds*1000), e -> {
                    // Ein Jahr hat zwölf Monate
                    if(month.getValue() < 12){
                        month.setValue(month.getValue() + 1);
                    } else {
                        month.setValue(1);
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE); // Endlosschleife
        timeline.play(); // Timeline starten
    }

    /**
     * Getter für das eigentliche Scene-Objekt zum Laden auf der Stage.
     * @return die Scene für das Spiel
     */
    public Scene getGameScene() {
        return gameScene;
    }
}
