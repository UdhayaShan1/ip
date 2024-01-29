package utilities;

import exceptions.RyanGoslingException;
import utilities.MessagePrinter;
import utilities.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class RyanGosling extends Application {
    public static String chatBotName = "RyanGosling";

    public RyanGosling() {

    }
    public static void main(String[] args) throws RyanGoslingException, FileNotFoundException {
        MessagePrinter.greeting(chatBotName);
        //We begin listening
        Ui botDispatcher = new Ui();
        botDispatcher.chatListener();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.
    }
}
