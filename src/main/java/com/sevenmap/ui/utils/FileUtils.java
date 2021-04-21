package com.sevenmap.ui.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

/**
 * File opening routine manager.
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * Load a text file to a string.
     * @param path path to file
     * @return string containing the file's content
     */
    public static String loadString(String path) {
        String output = "default";
        try {
            ClassLoader cl = FileUtils.class.getClassLoader();
            File file = new File(cl.getResource(path).getPath());

            output =  new String(Files.readAllBytes(file.toPath()));
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
            ClassLoader cl = FileUtils.class.getClassLoader();

            return ImageIO.read(new File(cl.getResource(path).getFile()));
        } catch(IOException e) {
            System.err.printf("File at path %s could not be found", path);
            System.exit(1);
        } catch(Exception e) {
            System.err.printf("Loading image at path %s failed : %s%n%s", path, e.getMessage(), e.getStackTrace());
            System.exit(1);
        }
        return null;
    }
}
