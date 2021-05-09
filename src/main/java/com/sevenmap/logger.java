import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class logger {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("login");
        FileHandler fileHandler;
        try {
          fileHandler = new FileHandler("C:\\logfile.log", true);
          logger.addHandler(fileHandler);
          logger.setLevel(Level.ALL);
          SimpleFormatter formatter = new SimpleFormatter();
          fileHandler.setFormatter(formatter);
          logger.log(Level.WARNING, "Warning message");
          logger.log(Level.SEVERE, "Severe message");
          logger.log(Level.INFO, "Info message from Throwable object", new Throwable("Throwable exception"));
      } catch (SecurityException se) {
          System.out.println("SecurityException was catched " + se.getMessage());
      } catch (IOException ioe) {
          System.out.println("IOException was catchted " + ioe.getMessage());
      }
    }
}