import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
//import java.awt.*;

public class aiva extends Application {

    //Set common variables
    private Stage AIVA;
    private String admin_uName = "root";
    private String admin_Name = "root";
    private String admin_Pass = "root";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set stage
        AIVA = primaryStage;
        AIVA.setTitle("A.I.V.A.");
        AIVA.setOnCloseRequest(e->{
            e.consume();
            exit();
        });

            //Scene 1 (login - initial)
        //Set login grid layout
        GridPane login_grid = new GridPane();
        login_grid.setPadding(new Insets(10));
        login_grid.setVgap(10);
        login_grid.setHgap(10);

        //User name prompt
        Label uName_prompt1 = new Label("Username:");
        GridPane.setConstraints(uName_prompt1, 0, 0);

        //User name input
        TextField uName_in1 = new TextField();
        uName_in1.setPromptText("Username");
        GridPane.setConstraints(uName_in1, 1, 0);

        //User password prompt
        Label Pass_prompt1 = new Label("Password:");
        GridPane.setConstraints(Pass_prompt1, 0, 1);

        //User password input
        TextField Pass_in1 = new TextField();
        Pass_in1.setPromptText("Password");
        GridPane.setConstraints(Pass_in1, 1, 1);

        //Submit button
        Button submit_B1 = new Button("Submit");
        submit_B1.setOnAction(e->submit(uName_in1.getText(), Pass_in1.getText()));
        submit_B1.defaultButtonProperty().bind(submit_B1.focusedProperty());
        GridPane.setConstraints(submit_B1, 0, 2);

        //Save user check
        CheckBox uSave_C1 = new CheckBox("Keep me logged in");
        uSave_C1.setSelected(true);
        uSave_C1.setOnAction(e->{
            if (uSave_C1.isSelected()) {
                System.out.println("Terminal: Username will be saved upon exit");
            }
            else {
                System.out.println("Terminal: Username will be forgotten upon exit");
            }
        });
        GridPane.setConstraints(uSave_C1, 1, 2);

        //Set scene
        login_grid.getChildren().addAll(uName_prompt1, uName_in1, Pass_prompt1, Pass_in1, submit_B1, uSave_C1);
        Scene login_scene = new Scene(login_grid, 235, 115);

        //Show scene
        AIVA.setScene(login_scene);
        AIVA.show();

    }

    private void submit(String name, String pass) {
        //Determine user credibility
        if (!name.equals(admin_uName)) {
            System.out.println("Terminal: Username '"+name+"' is incorrect");
        }
        if (!pass.equals(admin_Pass)) {
            System.out.println("Terminal: Password '"+pass+"' is incorrect");
        }
        if (!name.equals(admin_uName) || !pass.equals(admin_Pass)) {
            System.out.println("Terminal: Access denied");
        }
        if (name.equals(admin_uName) && pass.equals(admin_Pass)) {
            System.out.println("Terminal: Access granted");
            System.out.println("AIVA: Welcome, "+admin_Name+"!");
            //AIVA.setScene(home_scene);
        }
    }

    private void exit() {
        //Shut down procedure
        System.out.println("Terminal: Terminating systems...");
        System.out.println("AIVA: Goodbye!");
        AIVA.close();
    }
}
