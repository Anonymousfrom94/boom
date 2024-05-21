package universite_paris8.iut.osall.boom.modele.vue;

import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.osall.boom.modele.Map;

public class VueMap {

    private ImageView imageView;
    private Image dcMur;
    private Image dcHautPorteRond;
    private Image dcBasPorte;
    private TilePane tilePane;
    private Map map;

    public VueMap(TilePane tilePane, Map map){

        this.tilePane = tilePane;
        this.map = map;

        this.dcMur = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgBloc/DarkCastleMur.png");
        this.dcHautPorteRond = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgBloc/DarkCastleHautArrondi.png");
        this.dcBasPorte = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgBloc/DarkCastleBasPorte.png");

        for (int i = 0; i < map.getTableau().length;i++){
            imageView = new ImageView();
            switch (map.getTableau()[i]){
                case 1 -> imageView.setImage(dcMur);
                case 13 -> imageView.setImage(dcHautPorteRond);
                case 19 -> imageView.setImage(dcBasPorte);
            }
            this.tilePane.getChildren().add(imageView);
        }
    }



}
