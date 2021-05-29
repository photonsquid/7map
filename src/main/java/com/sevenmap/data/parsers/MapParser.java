package com.sevenmap.data.parsers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import com.sevenmap.core.Props;
import com.sevenmap.core.Props.BUILD_TYPE;
import com.sevenmap.data.objsept.GeographicCoord;
import com.sevenmap.data.objsept.PlainMap;
import com.sevenmap.data.parsers.geojson.GeoJson;
import com.sevenmap.data.parsers.osm.OSM;
import com.sevenmap.spinel.elements.geom.Item;
import com.sevenmap.spinel.math.Vector3f;

import org.apache.commons.io.FilenameUtils;

public abstract class MapParser extends Parser {

  // <-------------------------------- Attibutes -------------------------------->
  protected Props props;
  protected PlainMap<Long, Item> generatedMap;

  // <------------------------------- Constructor ------------------------------->
  public MapParser() {
  }

  public MapParser(Props props) {
    this.props = props;
  }
  // <-------------------------------- Code logic ------------------------------->

  /**
   * <ul>
   * <li>Download a map from an URL</li>
   * <li>{@code props.downloadURL} has to be set in props !</li>
   * <li>{@code props.hasToBuild} has to be set to
   * {@code Props.BUILD_TYPE.FROM_URL} !</li>
   * <li>Download it into {@code props.getAppDataPath() + "maps/"}</li>
   * <li>if {@code Props.MapFile} is null, or the file already exists use name
   * from server.</li>
   * </ul>
   */
  final public void downloadMap() {
    // Download file from any url
    if (props.hasToBuild().equals(BUILD_TYPE.FROM_URL)) {
      String fileName = props.getMapFile().toString();
      if (fileName == null) {
        fileName = getFileNameFromUrl(props.getDownloadURL());
      }
      String filePath = props.getAppDataPath() + "maps/" + fileName;
      File mapFile = new File(filePath);

      while (mapFile.exists()) {
        Double nb = Math.random() * 9;
        filePath = filePath + nb;
        mapFile = new File(filePath);
      }

      try {
        mapFile.createNewFile();
        FileOutputStream fileOS = new FileOutputStream(mapFile);
        BufferedInputStream inputStream = new BufferedInputStream(props.getDownloadURL().openStream());
        byte data[] = new byte[1024];
        int byteContent;
        while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
          fileOS.write(data, 0, byteContent);
        }
        fileOS.close();
      } catch (IOException ex) {
      } finally {
      }
      // TODO handle exception
    }
  }

  public static URL generateURL(Props props) throws MalformedURLException {
    // TODO change it to unimplementedMethodException
    // note pour @seba1204 :
    // Je ne peux pas mettre une méthode static et abstraite...
    // Il me la faut absolument static, donc elle ne sera pas
    // abstraite. Du coup il faut espérer que toute les classes
    // qui implémentent cette classe Override cette méthode
    // C'est pourquoi il faut générer une RuntimeError (ou l'autre je sais plus)
    throw new MalformedURLException();
  };

  public abstract void parse();

  public abstract void build();

  /**
   * Store the generated map into the database. <br>
   * Has to be exectuted after parsing and building map !
   */
  final public void store() {
    if (generatedMap != null) {

      // TODO
    }
  };

  /**
   * Convert geopgraphic coordinates into regular coords that are dislayable
   * 
   * @param coords geopgraphic coordinates to convert
   * @return Vector3f of coordinates converted
   */
  public static Vector3f GeoCoord2SpinelCoord(GeographicCoord coords) {
    Double x_proj = Math.cos(0.0) * (coords.getLat());
    Double y_proj = coords.getLon();
    Float xtemp = x_proj.floatValue() - 43.60f;
    Float ytemp = y_proj.floatValue() - 1.45f;
    Float x = 10000 * xtemp - 22;
    Float y = 0f;
    Float z = 10000 * ytemp - 35;

    return new Vector3f(x, y, z);
  }

  /**
   * Convert regular coords that are dislayable into geopgraphic coordinates
   * 
   * @param vect Vector3f of coordinates
   * @return GeographicCoord of coordinates converted
   */
  public static GeographicCoord SpinelCoord2GeoCoord(Vector3f vect) {
    Double lat = (double) vect.getX();
    Double lon = (double) vect.getZ();
    return new GeographicCoord(lat, lon);
  }

  /**
   * {@link Props.hasToBuild} of props has to be {@link BUILD_TYPE.FROM_FILE} or
   * {@link BUILD_TYPE.FROM_URL}, and {@link Props.MapFile} has to be a valid
   * path, or {@link Props.downloadURL} has to be a valid URL
   * 
   * @param props current state of the App
   * @return an ExternalMap with the right type
   */
  public static final MapParser GenerateRightMapType(Props props) {
    String extension = null;
    if (props.hasToBuild().equals(BUILD_TYPE.FROM_FILE)) {
      // get extension of file
      String path = props.getMapFile();
      if (path == null) {
        path = props.getDefaultMapFile();
      }
      extension = FilenameUtils.getExtension(path);

    } else if (props.hasToBuild().equals(BUILD_TYPE.FROM_URL)) {
      extension = FilenameUtils.getExtension(getFileNameFromUrl(props.getDownloadURL()));
    }

    if (extension == null) {
      // TODO handle error, URL or file not valid
      System.out.println("ERREUR URL or file not valid");
    }

    switch (extension) {
      case "osm":
        return new OSM(props);
      case "geojson":
      case "json":
        return new GeoJson(props);
      default:
        break;
    }

    return null;
  }

  // <--------------------------- Getter and setters ---------------------------->
  public Props getProps() {
    return this.props;
  }

  public void setProps(Props props) {
    this.props = props;
  }

  public MapParser props(Props props) {
    setProps(props);
    return this;
  }

  public PlainMap<Long, Item> getGeneratedMap() {
    return this.generatedMap;
  }

  public void setGeneratedMap(PlainMap<Long, Item> generatedMap) {
    this.generatedMap = generatedMap;
  }

  // <---------------------------- Object overrides ----------------------------->
  @Override
  public String toString() {
    return "{" + " props='" + getProps() + "'" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof MapParser)) {
      return false;
    }
    MapParser externalMap = (MapParser) o;
    return Objects.equals(props, externalMap.props);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(props);
  }

}
