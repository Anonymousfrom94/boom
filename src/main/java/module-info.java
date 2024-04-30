module com.example.boom {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.osall.boom to javafx.fxml;
    exports universite_paris8.iut.osall.boom;
}