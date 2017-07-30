package Test;

//Import necessary java libraries
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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

class sandbox_user {

    //Variables

    static void show() {
        Stage AIVA = new Stage();
        AIVA.initModality(Modality.APPLICATION_MODAL);
        AIVA.setTitle("A.I.V.A.");
        AIVA.setMinHeight(250);
        AIVA.setMinWidth(250);

        BorderPane user_layout = new BorderPane();
        Label user_info = new Label("User Information");
        user_info.setAlignment(Pos.CENTER);
        Label user_Name = new Label("User Name: ");
        Button done = new Button("Done");
        done.setOnAction(e->AIVA.close());

        user_layout.setTop(user_info);
        user_layout.setCenter(user_Name);
        user_layout.setBottom(done);

        Scene user_scene = new Scene(user_layout);
        AIVA.setScene(user_scene);
        AIVA.showAndWait();
    }

}
