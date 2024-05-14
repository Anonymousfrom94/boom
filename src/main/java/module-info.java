module com.example.boom {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens universite_paris8.iut.osall.boom to javafx.fxml;
    exports universite_paris8.iut.osall.boom;
    exports universite_paris8.iut.osall.boom.controller;
    opens universite_paris8.iut.osall.boom.controller to javafx.fxml;
}