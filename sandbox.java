package Test;

//Import necessary java libraries
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.Scene;
//import java.util.prefs.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;

public class sandbox extends Application {
    //Define Stage
    private Stage AIVA;

    //Define Scenes
    private Scene Home_scene;
    private Scene Web_scene;
    private Scene iLogin_scene;
    private Scene Login_scene;

    //Define Admin user info
    private User admin = new User();
    private String admin_uName;
    private String admin_Pass;
    private String admin_Name;

    public static void main(String[] args) {
        //Launch AIVA
        System.out.println("Terminal: Initiating AIVA...");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set Admin user info
        admin.uNameProperty().addListener((v, oldValue, newValue)->{
            System.out.println("Terminal: Username set to "+newValue);
            System.out.println("Username property: "+admin.uNameProperty());
        });
        admin.passProperty().addListener((v, oldValue, newValue)->{
            System.out.println("Terminal: User password set to "+newValue);
            System.out.println("Password property: "+admin.passProperty());
        });
        admin.nameProperty().addListener((v, oldValue, newValue)->{
            System.out.println("Terminal: User name set to "+newValue);
            System.out.println("User name property: "+admin.nameProperty());
        });
        admin.setuName("root");
        admin_uName = admin.getuName();
        admin.setPass("root");
        admin_Pass = admin.getPass();
        admin.setName("root");
        admin_Name = admin.getName();

        //Set web access
        WebView AIVA_web = new WebView();

        //Set stage and title
        AIVA = primaryStage;
        AIVA.getIcons().add(new Image(getClass().getResourceAsStream("ai_image.png")));
        AIVA.setTitle("A.I.V.A.");

        //Set close request procedure
        AIVA.setOnCloseRequest(e->{
            e.consume();
            exit();
        });

            //Scene 1 - Home
        //Set home layout
        BorderPane Home_layout = new BorderPane();

        //Set home contents
            //Menu bar
        MenuBar Home_mBar = menu_barCreate();
        //TEMPORARY
            //Command field
        TextField user_com = new TextField();
        user_com.setPromptText("https://<website>");
        //user_com.prefWidthProperty().bind(AIVA.widthProperty());
        Button com_submit = new Button("Submit");
        com_submit.setDefaultButton(true);
        com_submit.setOnAction(e->{
            System.out.println("Terminal: Loading user request...");
            System.out.println("User: "+user_com.getText());
            if (user_com.getText().startsWith("http://") || user_com.getText().startsWith("https://")){
                AIVA_web.getEngine().load(user_com.getText());
            }
            else if (user_com.getText().endsWith(".com") || user_com.getText().endsWith(".org") ||
                    user_com.getText().endsWith(".net")) {
                AIVA_web.getEngine().load("https://"+user_com.getText());
            }
            else {
                AIVA_web.getEngine().load("https://"+user_com.getText()+".com");
            }
            BorderPane Web_layout = new BorderPane();
            HBox Web_sub = new HBox();
            com_submit.setDefaultButton(true);
            Web_sub.getChildren().addAll(user_com, com_submit, Home_mBar);
            HBox.setHgrow(user_com, Priority.ALWAYS);
            //Web_sub.prefWidthProperty().bind(AIVA.widthProperty());
            Web_layout.setTop(Web_sub);
            Web_layout.setCenter(AIVA_web);
            user_com.setPrefWidth(545);
            Web_scene = new Scene(Web_layout);
            AIVA.setScene(Web_scene);
            //AIVA.setMaximized(true);
            AIVA.centerOnScreen();
            AIVA.setResizable(true);
            AIVA.show();
        });
        //TEMPORARY
            //Log-out button
        Button logOut_button = new Button("Log Out");
        logOut_button.setOnAction(e->returnTo_loginIn());
            //Input array
        HBox Com_sub = new HBox();
        Com_sub.getChildren().addAll(user_com, com_submit, logOut_button);
        HBox.setHgrow(user_com, Priority.ALWAYS);

        //Create home scene
        Home_layout.setTop(Home_mBar);
        Home_layout.setCenter(Com_sub);
        Home_scene = new Scene(Home_layout, 300, 55);
        Home_scene.getStylesheets().add("/Test/home_darkTheme.css");

        //--------------------------------------------------------------------------------------------------------------

            //Scene 2 - Incorrect Login (iLogin)
        GridPane iLogin_grid = login_gridCreate("i");
        //Create Incorrect Login Scene
        iLogin_scene = new Scene(iLogin_grid, 280, 170);
        iLogin_scene.getStylesheets().add("/Test/login_darkTheme.css");

        //--------------------------------------------------------------------------------------------------------------

            //Scene 3 - Login
        GridPane Login_grid = login_gridCreate("o");
        //Create Login Scene
        Login_scene = new Scene(Login_grid, 231, 135);
        Login_scene.getStylesheets().add("/Test/login_darkTheme.css");

        //Set scene and show
        AIVA.setScene(Login_scene);
        AIVA.setResizable(false);
        AIVA.show();
    }

    private void exit() {
        //System.out.println("Terminal: Terminating systems...");
        //System.out.println("AIVA: Goodbye!");
        AIVA.close();
    }

    private void set_grid(GridPane grid_name, int pad, int vGap, int hGap) {
        grid_name.setPadding(new Insets(pad));
        grid_name.setVgap(vGap);
        grid_name.setHgap(hGap);
    }

    private MenuBar menu_barCreate() {
        //File menu
        Menu File_menu = file_menuCreate();

        //Edit menu
        Menu Edit_menu = edit_menuCreate();

        //View menu
        Menu View_menu = view_menuCreate();

        //Help menu
        Menu Help_menu = help_menuCreate();

        //Menu Bar Build
        MenuBar Home_mBar = new MenuBar();
        Home_mBar.getMenus().addAll(File_menu, Edit_menu, View_menu, Help_menu);
        return Home_mBar;
    }

    private Menu file_menuCreate() {
        Menu File_menu = new Menu("_File");

            //File -> User
        MenuItem File_user = new MenuItem("User...");
        File_user.setOnAction(e->File_userFunk());
        //File_user.setDisable(true);
            //File -> Settings
        MenuItem File_settings = new MenuItem("Settings...");
        File_settings.setOnAction(e->File_settingsFunk());
        //File_settings.setDisable(true);

        //File Menu Splitter
        SeparatorMenuItem File_split1 = new SeparatorMenuItem();

            //File -> Command Prompt
        MenuItem File_cmd = new MenuItem("C-Prompt");
        File_cmd.setOnAction(e->File_cmdFunk());
        File_cmd.setDisable(true);
            //File -> Toggle Auto Logging
        CheckMenuItem File_autoLog = new CheckMenuItem("Auto Log");
        File_autoLog.setOnAction(e->toggle_autoLogging(File_autoLog));
        //File_autoLog.setDisable(true);
        File_autoLog.setSelected(true);

        //File Menu Splitter
        SeparatorMenuItem File_split2 = new SeparatorMenuItem();

            //File -> Exit
        MenuItem File_exit = new MenuItem("Exit");
        File_exit.setOnAction(e->exit());
        //File_exit.setDisable(true);

        //File Menu Build
        File_menu.getItems().addAll(File_user, File_settings, File_split1, File_cmd, File_autoLog, File_split2,
                File_exit);
        return File_menu;
    }

    private Menu edit_menuCreate() {
        Menu Edit_menu = new Menu("_Edit");

            //Edit -> Calibrate
        MenuItem Edit_calibrate = new MenuItem("Calibrate");
        Edit_calibrate.setOnAction(e->Edit_calibrateFunk());
        //Edit_calibrate.setDisable(true);
            //Edit -> Test
        MenuItem Edit_test = new MenuItem("Test...");
        Edit_test.setOnAction(e->Edit_testFunk());
        Edit_test.setDisable(true);

        //Edit Menu Splitter
        SeparatorMenuItem Edit_split1 = new SeparatorMenuItem();

            //Edit -> Preferences
        MenuItem Edit_preferences = new MenuItem("Preferences...");
        Edit_preferences.setOnAction(e->Edit_preferencesFunk());
        //Edit_preferences.setDisable(true);
            //Edit -> Personalize
        MenuItem Edit_personalize = new MenuItem("Personalize...");
        Edit_personalize.setOnAction(e->Edit_personalizeFunk());
        //Edit_personalize.setDisable(true);

        //Edit Menu Splitter
        SeparatorMenuItem Edit_split2 = new SeparatorMenuItem();

            //Edit -> Layout
        MenuItem Edit_layout = new MenuItem("Layout...");
        Edit_layout.setOnAction(e->Edit_layoutFunk());
        //Edit_layout.setDisable(true);

        //Edit Menu Build
        Edit_menu.getItems().addAll(Edit_calibrate, Edit_test, Edit_split1, Edit_preferences, Edit_personalize,
                Edit_split2, Edit_layout);
        return Edit_menu;
    }

    private Menu view_menuCreate() {
        Menu View_menu = new Menu("_View");

            //View -> Theme menu
        Menu View_theme = theme_menuCreate();
        //View_theme.setDisable(true);
            //View -> Tools
        MenuItem View_tools = new MenuItem("Tools");
        View_tools.setOnAction(e->View_toolsFunk());
        View_tools.setDisable(true);

        //View Menu Splitter
        SeparatorMenuItem View_split1 = new SeparatorMenuItem();

            //View -> Terminal
        MenuItem View_terminal = new MenuItem("Terminal");
        View_terminal.setOnAction(e->View_terminalFunk());
        View_terminal.setDisable(true);
            //View -> Logs
        MenuItem View_logs = new MenuItem("Logs");
        View_logs.setOnAction(e->View_logsFunk());
        View_logs.setDisable(true);

        //View Menu Splitter
        SeparatorMenuItem View_split2 = new SeparatorMenuItem();

            //View -> Source menu
        Menu View_source = source_menuCreate();
        //View_source.setDisable(true);
            // View -> Docs menu
        Menu View_docs = docs_menuCreate();
        //View_docs.setDisable(true);

        //View Menu Build
        View_menu.getItems().addAll(View_theme, View_tools, View_split1, View_terminal, View_logs, View_split2,
                View_source, View_docs);
        return View_menu;
    }

    private Menu theme_menuCreate() {
        Menu View_theme = new Menu("Theme");
        ToggleGroup Theme_toggle = new ToggleGroup();

            //Theme -> Toggle Light
        RadioMenuItem Theme_light = new RadioMenuItem("Light");
        Theme_light.setToggleGroup(Theme_toggle);
        Theme_light.setOnAction(e->toggle_lightTheme(Theme_light, Home_scene));
        //Theme_light.setDisable(true);
            //Theme -> Toggle Dark
        RadioMenuItem Theme_dark = new RadioMenuItem("Dark");
        Theme_dark.setToggleGroup(Theme_toggle);
        Theme_dark.setOnAction(e->toggle_darkTheme(Theme_dark, Home_scene));
        //Theme_dark.setDisable(true);
            //Theme -> Add Theme
        MenuItem Theme_add = new MenuItem("Add");
        Theme_add.setOnAction(e->Theme_addFunk());
        //Theme_add.setDisable(true);

        //Default Theme
        Theme_dark.setSelected(true);

        //View -> Theme Menu Build
        View_theme.getItems().addAll(Theme_light, Theme_dark, Theme_add);
        return View_theme;
    }

    private Menu source_menuCreate() {
        Menu View_source = new Menu("Source");

            //Source -> Login/Home
        MenuItem Source_home = new MenuItem("Login/Home");
        Source_home.setOnAction(e->Source_homeFunk());
        //Source_home.setDisable(true);
            //Source -> Settings
        MenuItem Source_settings = new MenuItem("Settings");
        Source_settings.setOnAction(e->Source_settingsFunk());
        //Source_settings.setDisable(true);
            //Source -> Learning
        MenuItem Source_learning = new MenuItem("Learning");
        Source_learning.setOnAction(e->Source_learningFunk());
        Source_learning.setDisable(true);

        //Source Menu Build
        View_source.getItems().addAll(Source_home, Source_settings, Source_learning);
        return View_source;
    }

    private Menu docs_menuCreate() {
        Menu View_docs = new Menu("Docs");

            //Docs -> Read Me
        MenuItem Docs_readMe = new MenuItem("Read Me");
        Docs_readMe.setOnAction(e->Docs_readMeFunk());
        //Docs_readMe.setDisable(true);
            //Docs -> Documentation
        MenuItem Docs_documentation = new MenuItem("Documentation");
        Docs_documentation.setOnAction(e->Docs_documentationFunk());
        //Docs_documentation.setDisable(true);

        //Docs Menu Build
        View_docs.getItems().addAll(Docs_readMe, Docs_documentation);
        //View_documentation.setDisable(true);
        return View_docs;
    }

    private Menu help_menuCreate() {
        Menu Help_menu = new Menu("_Help");

            //Help -> Find
        MenuItem Help_find = new MenuItem("Find");
        Help_find.setOnAction(e->Help_findFunk());
        Help_find.setDisable(true);
            //Help -> Commands menu
        Menu Help_commands = commands_menuCreate();
        //Help_commands.setDisable(true);

        //Help Menu Splitter
        SeparatorMenuItem Help_split1 = new SeparatorMenuItem();

            //Help -> About
        MenuItem Help_about = new MenuItem("About");
        Help_about.setOnAction(e->Help_aboutFunk());
        //Help_about.setDisable(true);

        //Help Menu Build
        Help_menu.getItems().addAll(Help_find, Help_commands, Help_split1, Help_about);
        return Help_menu;
    }

    private Menu commands_menuCreate() {
        Menu Help_commands = new Menu("Commands");

            //Commands -> Add
        MenuItem Commands_add = new MenuItem("Add");
        Commands_add.setOnAction(e->Commands_addFunk());
        Commands_add.setDisable(true);
            //Commands -> Current
        MenuItem Commands_current = new MenuItem("Current");
        Commands_current.setOnAction(e->Commands_currentFunk());
        //Commands_current.setDisable(true);
            //Commands -> Deleted
        MenuItem Commands_deleted = new MenuItem("Deleted");
        Commands_deleted.setOnAction(e->Commands_deletedFunk());
        Commands_deleted.setDisable(true);

        //Help -> Commands Menu Build
        Help_commands.getItems().addAll(Commands_add, Commands_current, Commands_deleted);
        return Help_commands;
    }

    private void returnTo_loginIn() {
        System.out.println("Terminal: Logging user out...");
        AIVA.setScene(Login_scene);
    }

    private GridPane login_gridCreate(String i) {
        //Set iLogin grid
        GridPane iLogin_grid = new GridPane();
        set_grid(iLogin_grid, 10, 10, 10);

        //Set iLogin contents
            //Welcome prompt 1
        Label welcome_note = new Label("Welcome, ");

            //Incorrect prompt 1
        Label iPrompt_note = new Label("Incorrect username or password.");
        iPrompt_note.setStyle("-fx-text-fill: red");
        if (i.equals("i")) {
            GridPane.setConstraints(iPrompt_note, 1, 0);
            GridPane.setHalignment(iPrompt_note, HPos.LEFT);
        }

            //User name prompt
        Label iuName_prompt = new Label("Username: ");
        GridPane.setConstraints(iuName_prompt, 0, 1);
        GridPane.setHalignment(iuName_prompt, HPos.RIGHT);

            //User name input field
        TextField iuName_in = new TextField();
        iuName_in.setPromptText("Username");
        GridPane.setConstraints(iuName_in, 1, 1);

            //Welcome prompt 2
        Label welcome_name = new Label();
        HBox welcome_text = new HBox(welcome_note, welcome_name);
        welcome_text.setAlignment(Pos.CENTER);
        if (i.equals("o")) {
            GridPane.setConstraints(welcome_text, 1, 0);
            welcome_name.textProperty().bind(iuName_in.textProperty());
        }

            //Password prompt
        Label iPass_prompt = new Label("Password: ");
        GridPane.setConstraints(iPass_prompt, 0, 2);
        GridPane.setHalignment(iPass_prompt, HPos.RIGHT);

            //Password input field
        PasswordField iPass_in = new PasswordField();
        iPass_in.setPromptText("Password");
        GridPane.setConstraints(iPass_in, 1, 2);

            //Incorrect prompt 2
        Label iPrompt_try = new Label("Please try again.");
        iPrompt_try.setStyle("-fx-text-fill: red");
        if (i.equals("i")) {
            GridPane.setConstraints(iPrompt_try, 1, 3);
            GridPane.setHalignment(iPrompt_try, HPos.LEFT);
        }

            //Submit button
        Button iSubmit_button = new Button("Submit");
        iSubmit_button.setOnAction(e->verify_login(iuName_in.getText(), iPass_in.getText()));
        //iSubmit_button.defaultButtonProperty().bind(iSubmit_button.focusedProperty());
        iSubmit_button.setDefaultButton(true);
        if (i.equals("i")) {
            GridPane.setConstraints(iSubmit_button, 0, 4);
        }
        else {
            GridPane.setConstraints(iSubmit_button, 0, 3);
        }

            //User-save checkbox
        CheckBox iUser_save = new CheckBox("Keep me logged in");
        iUser_save.setOnAction(e->toggle_saveMode(iUser_save));
        iUser_save.setSelected(true);
        if (i.equals("i")) {
            GridPane.setConstraints(iUser_save, 1, 4);
        }
        else {
            GridPane.setConstraints(iUser_save, 1, 3);
        }

        //Set iLogin scene
        if (i.equals("i")) {
            iLogin_grid.getChildren().addAll(iPrompt_note, iuName_prompt, iuName_in, iPass_prompt, iPass_in,
                    iPrompt_try, iSubmit_button, iUser_save);
        }
        else {
            iLogin_grid.getChildren().addAll(welcome_text, iuName_prompt, iuName_in, iPass_prompt, iPass_in, iSubmit_button,
                    iUser_save);
        }

        return iLogin_grid;
    }

    private void toggle_saveMode(CheckBox save_check) {
        if (save_check.isSelected()) {
            System.out.println("Save mode selected");
        }
        else {
            System.out.println("Save mode de-selected");
        }
    }

    private void verify_login(String uName, String Pass) {
        if (uName.equals(admin_uName) && Pass.equals(admin_Pass)) {
            System.out.println("Access granted");
            System.out.println("Welcome, "+admin_Name+"!");
            AIVA.setScene(Home_scene);
        }
        else {
            System.out.println("Access denied");
            AIVA.setScene(iLogin_scene);
        }
    }

    private void File_userFunk() {
        System.out.println("'User' selected");
    }

    private void File_settingsFunk() {
        System.out.println("'Settings' selected");
        sandbox_settings.show();
    }

    private void File_cmdFunk() {
        System.out.println("'C-Prompt' selected");
    }

    private void toggle_autoLogging(CheckMenuItem autoLog_check) {
        if (autoLog_check.isSelected()) {
            System.out.println("Auto-logging enabled");
        }
        else {
            System.out.println("Auto-logging disabled");
        }
    }

    private void Edit_calibrateFunk() {
        System.out.println("'Calibrate' selected");
    }

    private void Edit_testFunk() {
        System.out.println("'Test' selected");
    }

    private void Edit_preferencesFunk() {
        System.out.println("'Preferences' selected");
    }

    private void Edit_personalizeFunk() {
        System.out.println("'Personalize' selected");
    }

    private void Edit_layoutFunk() {
        System.out.println("'Layout' selected");
    }

    private void toggle_lightTheme(RadioMenuItem light_check, Scene scene_name) {
        if (light_check.isSelected()) {
            System.out.println("Light mode selected");
            scene_name.getStylesheets().clear();
            scene_name.getStylesheets().add("/Test/home_lightTheme.css");
        }
    }

    private void toggle_darkTheme(RadioMenuItem dark_check, Scene scene_name) {
        if (dark_check.isSelected()) {
            System.out.println("Dark mode selected");
            scene_name.getStylesheets().clear();
            scene_name.getStylesheets().add("/Test/home_darkTheme.css");
        }
    }

    private void Theme_addFunk() {
        System.out.println("'Add' selected");
    }

    private void View_toolsFunk() {
        System.out.println("'Tools' selected");
    }

    private void View_terminalFunk() {
        System.out.println("'Terminal' selected");
    }

    private void View_logsFunk() {
        System.out.println("'Logs' selected");
    }

    private void Source_homeFunk() {
        System.out.println("'Login/Home' selected");
    }

    private void Source_settingsFunk() {
        System.out.println("'Settings' selected");
    }

    private void Source_learningFunk() {
        System.out.println("'Learning' selected");
    }

    private void Docs_readMeFunk() {
        System.out.println("'Read Me' selected");
    }

    private void Docs_documentationFunk() {
        System.out.println("'Documentation' selected");
    }

    private void Help_findFunk() {
        System.out.println("'Find' selected");
    }

    private void Commands_addFunk() {
        System.out.println("'Add' selected");
    }

    private void Commands_currentFunk() {
        System.out.println("'Current' selected");
    }

    private void Commands_deletedFunk() {
        System.out.println("'Deleted' selected");
    }

    private void Help_aboutFunk() {
        System.out.println("'About' selected");
    }
}

