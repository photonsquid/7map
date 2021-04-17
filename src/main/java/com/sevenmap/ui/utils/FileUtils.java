package com.sevenmap.ui.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

/**
 * File opening routine manager.
 */
public class FileUtils {
    /**
     * Load a text file to a string.
     * @param path path to file
     * @return string containing the file's content
     */
    public static String loadString(String path) {
        String output = "default";
        try {
            output =  Files.readString(Path.of(path));
        } catch (IOException e) {
            System.err.printf("File at path %s could not be found", path);
            System.exit(1);
        }
        return output;
    }

    /**
     * Load an Image to a {@code BufferedImage} object
     * @param path path to image
     * @return BufferedImage object containing the color data
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch(IOException e) {
            System.err.printf("File at path %s could not be found", path);
            System.exit(1);
        } catch(Exception e) {
            System.err.printf("Something went terribly wrong...");
            System.exit(1);
        }
        return new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB); // unreachable code
    }
}
