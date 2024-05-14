package universite_paris8.iut.osall.boom.modele.vue;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.osall.boom.modele.Map;

public class VueMap {

    public VueMap(TilePane tilePane, Map map){
        double tileLargeur = tilePane.getPrefTileWidth();
        double tileHauteur = tilePane.getPrefTileHeight();
        for (int i = 0; i < map.getTableau().length;i++){

            Rectangle r = new Rectangle(tileLargeur, tileHauteur);
            if (map.getTableau()[i] == 14) {
                r.setFill(Color.GREEN);
            }else{
                r.setFill(Color.BLUE);
            }
            tilePane.getChildren().add(r);

        }
    }



}
