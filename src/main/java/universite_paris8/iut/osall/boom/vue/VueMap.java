package universite_paris8.iut.osall.boom.vue;

import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;

import java.io.FileReader;
import java.io.IOException;

public class VueMap {

    private TilePane tilePane;
    private Map map;
    private Image tileset;
    private Image[] tiles;

    public VueMap(TilePane tilePane, Map map){

        this.tilePane = tilePane;
        this.map = map;

        this.tileset = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgBloc/All.png");

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
        afficherMap();
    }

    public void afficherMap() {
        int[] m = loadMap("src/main/resources/universite_paris8/iut/osall/boom/map.json");
        this.map.setTableau(m);

        for (int i = 0; i < m.length; i++) {
            ImageView imageView = new ImageView();
            int tileIndex = m[i];
            if (tileIndex >= 1 && tileIndex <= tiles.length) { // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex - 1]);
            }
            tilePane.getChildren().add(imageView);
        }
    }

    public int[] loadMap(String filename) {
        int[] mapArray = null;

        try (FileReader reader = new FileReader(filename)) {
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            JSONArray layersArray = jsonObject.getJSONArray("layers");
            JSONObject firstLayer = layersArray.getJSONObject(0);
            JSONArray dataArray = firstLayer.getJSONArray("data");

            mapArray = new int[dataArray.length()];
            for (int i = 0; i < dataArray.length(); i++) {
                mapArray[i] = dataArray.getInt(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapArray;
    }

}
