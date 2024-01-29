package javafx;
import javafx.application.Application;
import utilities.RyanGosling;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(RyanGosling.class, args);
    }
}