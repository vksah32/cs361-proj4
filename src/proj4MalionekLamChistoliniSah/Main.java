/**
 * File: Main.java
 * @author Victoria Chistolini
 * @author Tiffany Lam
 * @author Joseph Malionek
 * @author Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* Main class that initializes and starts our javafx application */
public class Main extends Application {

    /**
     * Start method that loads the FXML file, sets the controller class
     * and initiates the stage.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        Controller controller = new Controller();
        loader.setController(controller);

        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 425);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> controller.cleanUpOnExit());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
