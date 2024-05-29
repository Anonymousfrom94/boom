package universite_paris8.iut.osall.boom.vue;

import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.osall.boom.modele.Map;

public class VueMap {

    private TilePane tilePane;
    private Map map;
    private Image tileset;
    private Image[] tiles;

    public VueMap(TilePane tilePane, Map map){

        this.tilePane = tilePane;
        this.map = map;

        this.tileset = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgBloc/maison2.png");

        int tileWidth = 16;
        int tileHeight = 16;
        int colonne = (int) (tileset.getWidth() / tileWidth);
        int ligne = (int) (tileset.getHeight() / tileHeight);
        tiles = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                tiles[y * colonne + x] = new WritableImage(tileset.getPixelReader(), x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }

        for (int i = 0; i < map.getTableau().length; i++) {
            ImageView imageView = new ImageView();
            int tileIndex = map.getTableau()[i];
            if (tileIndex >= 0 && tileIndex < tiles.length) {
                imageView.setImage(tiles[tileIndex-1]);
            }
            tilePane.getChildren().add(imageView);
        }
    }



}
