package Test;

//Import necessary java libraries
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.Scene;
//import java.util.prefs.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;

class sandbox_settings {

    //Variables

    static void show() {
        Stage AIVA = new Stage();
        AIVA.initModality(Modality.APPLICATION_MODAL);
        AIVA.setTitle("A.I.V.A.");
        AIVA.setMinHeight(250);
        AIVA.setMinWidth(250);

        BorderPane settings_layout = new BorderPane();
        Button test = new Button("Test");
        test.setOnAction(e->System.out.println("'Settings' button selected"));
        Button exit = new Button("Exit");
        exit.setOnAction(e->AIVA.close());

        settings_layout.setCenter(test);
        settings_layout.setBottom(exit);

        Scene settings_scene = new Scene(settings_layout);
        AIVA.setScene(settings_scene);
        AIVA.showAndWait();
    }
}
