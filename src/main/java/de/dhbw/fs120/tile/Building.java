package de.dhbw.fs120.tile;
// ich bin nicht sicher ob man diese Klasse bracuht, da hier nix passiert (noch nicht)
// Wird gebraucht! - jetzt passiert was

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Diese Klasse dient der Abbildung der Vererbungshierarchie zwischen Tiles und den Gebäuden im Spiel.
 * Gebäude besitzten ein zusätzliches Element zur grafischen Darstellung.
 * @author Lena Hammerer, Nick Hillebrand, Fabian Lulikat
 * @version 0.1.2
 *
 */
public abstract class Building extends Tile {
    /**
     * Layout-Container für den Hintergrund der Fläche, die das Gebäude einnehmen soll.
     */
    protected GridPane buildingArea = new GridPane();

    /**
     * Bei der Erzeugung eines Gebäudes wird die grafische Darstellung sowie die Größe festgelegt.
     * @param buildingView Der Bildausschnitt für die grafische Darstellung des Gebäudes
     * @param backgroundView Der Bildausschnitt für die grafische Darstellung des Grundstückes
     * @param width Die Breite des Grundstückes (angegeben in Anzahl an Kacheln im Raster)
     * @param height Die Höhe des Grundstückes (angegeben in Anzahl an Kacheln im Raster)
     * @param text Der Text zur Anzeige unter dem Gebäude zum bessere Verständnis der Funktion.
     */
    public Building(Rectangle2D buildingView, Rectangle2D backgroundView, int width, int height, String text) {
        // Bild für Gebäude
        super(buildingView);
        // Bilder für Hintergrund
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ImageView bgTile = new ImageView(TILESHEET);
                bgTile.setViewport(backgroundView);
                bgTile.setFitWidth(32);
                bgTile.setPreserveRatio(true);
                bgTile.setSmooth(true);
                bgTile.setCache(true);
                this.buildingArea.add(bgTile, i, j);
            }
        }
        // Text unter dem Gebäude
        Label lblBuilding = new Label(text);
        lblBuilding.setPadding(new Insets(48, 0, 0, 0));
        lblBuilding.setStyle("-fx-text-fill: #000000; -fx-font-size: 9pt");
        this.getChildren().addAll(this.buildingArea, lblBuilding);
        this.buildingArea.toBack(); // Hintergrund hinter das Gebäude
    }
}
