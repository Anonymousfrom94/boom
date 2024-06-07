package universite_paris8.iut.osall.boom.modele.Environnement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Map {

    private int[]tableau;

    public Map() {
        this.tableau = new int[10000];
    }



    public int[] getTableau() {
        return this.tableau;
    }

    public void setTableau(int[] tableau) {
        this.tableau = tableau;
    }
}
