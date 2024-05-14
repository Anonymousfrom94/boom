package universite_paris8.iut.osall.boom;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("vue2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Pane root = (Pane) scene.getRoot();
        root.requestFocus();
        stage.setTitle("DimentionBreaker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}