package com.sevenmap.ui.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
    public static String loadFile(String path) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        try (InputStream iS = classLoader.getResourceAsStream(path)) {
            if (iS == null) return null;
            try (InputStreamReader iSR = new InputStreamReader(iS);
                BufferedReader reader = new BufferedReader(iSR)) {
                    return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        
        } catch (IOException e) {
            System.err.printf("File at path %s could not be found", path);
            System.exit(1);
        }
        return null;
    }

    /**
     * Load an Image to a {@code BufferedImage} object
     * @param path path to image
     * @return BufferedImage object containing the color data
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(FileUtils.class.getClassLoader().getResourceAsStream(path));
        } catch(IOException e) {
            System.err.printf("File at path %s could not be found", path);
            System.exit(1);
        }
        return null;
    }
}
