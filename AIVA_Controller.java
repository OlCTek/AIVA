package AIVA;

//Import java packages
import java.io.IOException;
import java.util.ResourceBundle;

//Import javafx packages
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AIVA_Controller implements Initializable {

    //Establish FXML login variables
    @FXML
    private TextField username_input;
    @FXML
    private TextField password_input;
    @FXML
    private Button submit_button;

    //Establish FXML home menu functions
    @FXML
    public void handleFileUser() {
        System.out.println("Terminal: 'User' selected");
    }

    @FXML
    public void handleFileSettings() {
        System.out.println("Terminal: 'Settings selected");
    }

    @FXML
    public void handleFileCMD_Prompt() {
        System.out.println("Terminal: 'CMD Prompt' selected");
    }

        //AUTOLOG

    @FXML
    public void handleFileExit() {
        System.out.println("Terminal: 'Exit' selected");
    }

    @FXML
    public void handleEditCalibrate() {
        System.out.println("Terminal: 'Calibrate' selected");
    }

    @FXML
    public void handleEditTest() {
        System.out.println("Terminal: 'Test' selected");
    }

    @FXML
    public void handleEditPreferences() {
        System.out.println("Terminal: 'Preferences' selected");
    }

    @FXML
    public void handleEditPersonalize() {
        System.out.println("Terminal: 'Personalize' selected");
    }

    @FXML
    public void handleEditLayout() {
        System.out.println("Terminal: 'Layout' selected");
    }

    @FXML
    public void handleViewThemeLight() {
        System.out.println("Terminal: 'Light' theme selected");
    }

    @FXML
    public void handleViewThemeDark() {
        System.out.println("Terminal: 'Dark' theme selected");
    }

    @FXML
    public void handleViewThemeAdd() {
        System.out.println("Terminal: 'Add' theme selected");
    }

    @FXML
    public void handleViewTools() {
        System.out.println("Terminal: 'Tools' selected");
    }

    @FXML
    public void handleViewTerminal() {
        System.out.println("Terminal: 'Terminal' selected");
    }

    @FXML
    public void handleViewLogs() {
        System.out.println("Terminal: 'Logs' selected");
    }

    @FXML
    public void handleViewSourceMain() {
        System.out.println("Terminal: 'Main' source selected");
    }

    @FXML
    public void handleViewSourceController() {
        System.out.println("Terminal: 'Controller' source selected");
    }

    @FXML
    public void handleViewSourceLogin() {
        System.out.println("Terminal: 'Login' source selected");
    }

    @FXML
    public void handleViewSourceHome() {
        System.out.println("Terminal: 'Home' source selected");
    }

    @FXML
    public void handleViewDocsRead_Me() {
        System.out.println("Terminal: 'Read Me' document selected");
    }

    @FXML
    public void handleViewDocsDocumentation() {
        System.out.println("Terminal: 'Documentation' document selected");
    }

    @FXML
    public void handleHelpFind() {
        System.out.println("Terminal: 'Find' selected");
    }

    @FXML
    public void handleHelpCommandsAdd() {
        System.out.println("Terminal: 'Add' commands selected");
    }

    @FXML
    public void handleHelpCommandsCurrent() {
        System.out.println("Terminal: 'Current' commands selected");
    }

    @FXML
    public void handleHelpCommandsDeleted() {
        System.out.println("Terminal: 'Deleted' commands selected");
    }

    @FXML
    public void handleHelpAbout() {
        System.out.println("Terminal: 'About' selected");
    }

    //Establish FXML login functions
    @FXML
    public void loginVerif(ActionEvent event) throws IOException {

        //Establish local variables
        Stage AIVA_lStage;
        Parent AIVA_lParent;
        Scene AIVA_lScene;

        //If event originated from "submit_button" -> Check user credentials
        if (event.getSource()==submit_button) {
            String default_uName = "root";
            String default_Pass = "root";
            if (username_input.getText().equals(default_uName) && password_input.getText().equals(default_Pass)) {
                System.out.println("Terminal: Access granted");
                String default_Name = "root";
                System.out.println("AIVA: Welcome, "+ default_Name +"!");
                AIVA_lStage = (Stage) submit_button.getScene().getWindow();
                AIVA_lParent = FXMLLoader.load(getClass().getResource("AIVA_home.fxml"));
                AIVA_lScene = new Scene(AIVA_lParent, 200, 200);
                AIVA_lStage.setScene(AIVA_lScene);
                AIVA_lStage.show();
            }
            else {
                System.out.println("Terminal: Access denied");
                AIVA_lStage = (Stage) submit_button.getScene().getWindow();
                AIVA_lParent = FXMLLoader.load(getClass().getResource("AIVA_login.fxml"));
                AIVA_lScene = new Scene(AIVA_lParent, 230, 115);
                AIVA_lScene.getStylesheets().add("AIVA/login_darkTheme.css");
                AIVA_lStage.setScene(AIVA_lScene);
                AIVA_lStage.show();
            }
        }
    }

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        //Initialization notification
        //System.out.println("Terminal: Setting new window scene...");
    }
}
