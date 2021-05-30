package com.sevenmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sevenmap.core.Props;
import com.sevenmap.core.Runtime;
import com.sevenmap.core.cli.CLI;

/**
 * Main application class.
 */
public class App {

  public static void main(String[] args) {

    // Create new props
    Props props = new Props();

    // Parse args from CLI
    CLI.parseArgs(args, props);

    // Launch app
    Runtime rt = new Runtime();

    createStyleJson(props);

    rt.load(props);
  }

  private static void createStyleJson(Props props) {

    Path mainPath = Paths.get(props.getAppDataPath());

    if (!Files.exists(mainPath)) {
      try {
        Files.createDirectories(mainPath);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    File f = new File(props.getAppDataPath() + props.getSettingFile());
    if (!f.exists()) {
      InputStream stream = null;
      OutputStream resStreamOut = null;
      try {
        stream = App.class.getClassLoader().getResourceAsStream("maps/settings/styles.json");// note that each / is a
        // directory down in the
        // "jar tree" been the jar the root of the tree
        if (stream == null) {
          throw new Exception("Cannot get resource \"" + props.getSettingFile() + "\" from Jar file.");
        }

        int readBytes;
        byte[] buffer = new byte[4096];
        String path = props.getAppDataPath();
        resStreamOut = new FileOutputStream(path + props.getSettingFile());
        while ((readBytes = stream.read(buffer)) > 0) {
          resStreamOut.write(buffer, 0, readBytes);
        }
        stream.close();
        resStreamOut.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
    }
  }

}