package ui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * File opening routine manager.
 */
public class FileUtils {
    public static String loadToString(String path) {
        // StringBuilder output = new StringBuilder();
        // try (BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(path)))) {
        //     String line = "";
        //     while ((line = reader.readLine()) != null) {
        //         output.append(line).append("\n");
        //     }
        // } catch (IOException e) {
        //     System.err.printf("File at path %s could not be found", path);
        //     System.exit(1);
        // }
        // return output.toString();
        String output = "default";
        try {
            output =  Files.readString(Path.of(path));
        } catch (IOException e) {
            System.err.printf("File at path %s could not be found", path);
            System.exit(1);
        }
        return output;
    }
}
