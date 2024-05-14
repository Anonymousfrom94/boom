package universite_paris8.iut.osall.boom.controller;

import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.modele.map.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane PaneMap;
    @FXML private TilePane tilePane;
    private Joueur joueur;
    private Environnement env;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        tilePane.setPrefTileWidth(48);
        tilePane.setPrefTileHeight(48);
        this.env = new Environnement();
        this.joueur = new Joueur();
        spriteMap();
        spriteJoueur();
    }

    public void spriteMap(){
        double tileLargeur = this.tilePane.getPrefTileWidth();
        double tileHauteur = this.tilePane.getPrefTileHeight();
        for (int i = 0; i < this.env.getTableau().length;i++){

                Rectangle r = new Rectangle(tileLargeur, tileHauteur);
                if (env.getTableau()[i] == 14) {
                    r.setFill(Color.GREEN);
                }else{
                    r.setFill(Color.BLUE);
                }
                this.tilePane.getChildren().add(r);

        }
    }

    public void spriteJoueur(){
        Circle r = new Circle(10);
        r.setFill(Color.VIOLET);
        r.setId(String.valueOf(joueur.getId()));
        r.setTranslateX(320);
        r.setTranslateY(240);
        this.PaneMap.getChildren().add(r);
        clavier x  = new clavier(joueur);
        PaneMap.addEventFilter(KeyEvent.KEY_PRESSED, x);
        r.translateXProperty().bind(joueur.Xproperty());
        r.translateYProperty().bind(joueur.Yproperty());
    }

}