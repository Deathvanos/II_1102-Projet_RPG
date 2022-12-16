module com.example.projet_rpg {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    //  requires org.controlsfx.controls;
  //  requires com.dlsc.formsfx;
 //   requires org.kordamp.bootstrapfx.core;
  //  requires java.desktop;

    opens com.isep to javafx.fxml;
    exports com.isep;
    exports com.isep.controllers;
    opens com.isep.controllers to javafx.fxml;
}