package AIVA;

//Import javafx packages
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AIVA_Main extends Application {

    //start class
    @Override
    public void start(Stage AIVA_mStage) throws Exception {

        //Set window specifications
        Parent AIVA_mParent = FXMLLoader.load(getClass().getResource("AIVA_login.fxml"));
        Scene AIVA_mScene = new Scene(AIVA_mParent, 230, 115);
        AIVA_mScene.getStylesheets().add("/AIVA/login_darkTheme.css");
        AIVA_mStage.setTitle("AIVA");
        AIVA_mStage.getIcons().add(new Image(getClass().getResourceAsStream("ai_image.png")));
        AIVA_mStage.setScene(AIVA_mScene);

        //Launch window
        AIVA_mStage.show();
    }

    //main class
    public static void main(String[] args) {
        //Launch application
        Application.launch(AIVA_Main.class, args);
    }
}
