package universite_paris8.iut.osall.boom.controller;

import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.osall.boom.modele.Map;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.vue.VueJoueur;
import universite_paris8.iut.osall.boom.modele.vue.VueMap;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane pane;
    @FXML private TilePane tilePane;
    private Joueur joueur;
    private Map map;
    private VueMap vueMap;
    private VueJoueur vueJoueur;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
//        tilePane.setPrefTileWidth(16);
//        tilePane.setPrefTileHeight(16);
        this.map = new Map();
        this.joueur = new Joueur();
        this.vueMap = new VueMap(tilePane, map);
        this.vueJoueur = new VueJoueur(pane, joueur);
//        pane.requestFocus();
    }

    public void aff(MouseEvent mouseEvent) {
        pane.requestFocus();
    }
    //blablabla
}