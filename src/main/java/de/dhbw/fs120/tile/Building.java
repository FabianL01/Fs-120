package de.dhbw.fs120.tile;
// ich bin nicht sicher ob man diese Klasse bracuht, da hier nix passiert (noch nicht)

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Vector;

/**
 * Diese Klasse dient der Abbildung der Vererbungshierarchie zwischen Tiles und den Gebäuden im Spiel
 * @author Nick Hillebrand, Fabian Lulikat
 * @version 0.1
 *
 */
public abstract class Building extends Tile {
    protected GridPane buildingArea = new GridPane();

    public Building(){}
    public Building(Rectangle2D buildingView, Rectangle2D backgroundView, int width, int height) {
        super(buildingView);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ImageView bgTile = new ImageView(IMG);
                bgTile.setViewport(backgroundView);
                bgTile.setFitWidth(32);
                bgTile.setPreserveRatio(true);
                bgTile.setSmooth(true);
                bgTile.setCache(true);
                this.buildingArea.add(bgTile, i, j);
            }
        }

        this.getChildren().add(this.buildingArea);
        this.buildingArea.toBack();
    }
}
