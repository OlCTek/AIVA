package AIVA;

// java libraries
import java.io.IOException;

// javafx libraries
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Windows window = new Windows(); // Displays startup window
        window.showMain(primaryStage, true, false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
