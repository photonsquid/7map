package com.sevenmap.core;

import java.net.URL;
import java.util.Objects;

import com.sevenmap.data.parsers.osm.Structure.Bounds.Bounds;
import com.sevenmap.data.styles.Styles;

public class Props {

  public static enum BUILD_TYPE {
    FROM_URL, FROM_FILE, NONE
  }

  private String SettingFile;
  private String MapFile;
  private String defaultMapFile;
  private String AppDataPath;
  private String OSM_API_ROOT_URL;
  private String DatabseURL;
  private BUILD_TYPE hasToBuild;
  // Default bounds (arround ENSEEIHT school)
  private Double defaultMinLon;
  private Double defaultMaxLon;
  private Double defaultMinLat;
  private Double defaultMaxLat;

  private Double minLon;
  private Double maxLon;
  private Double minLat;
  private Double maxLat;

  private Styles styles;

  private Integer zoom;
  private Integer zoomStep;

  private URL downloadURL;

  // <------------------------------- Constructor ------------------------------->
  /**
   * Generate a prop object, with default values.<br>
   * It has to be filled by setters of its attribute.<br>
   * It will have a method to load it from a local file.
   */
  public Props() {
    this.defaultMapFile = "/maps/osm/n7.osm";
    this.OSM_API_ROOT_URL = "https://api.openstreetmap.org/api/0.6/map?bbox=";

    this.defaultMinLon = 1.45338;
    this.defaultMaxLon = 1.45760;
    this.defaultMinLat = 43.60116;
    this.defaultMaxLat = 43.60297;

    this.zoomStep = 1;

    this.zoom = 1; // unzoomed to the max
    this.AppDataPath = System.getProperty("user.home") + "/.7map/";

    SettingFile = "styles.json";
    MapFile = null;
    DatabseURL = "";
    hasToBuild = BUILD_TYPE.NONE;

    styles = new Styles();

  }

  // <-------------------------------- Code logic ------------------------------->
  public void zommIn() {
    zoom = zoom + zoomStep;
  }

  public void zommOut() {
    zoom = zoom - zoomStep;
  }

  // <--------------------------- Getter and setters --------------------------->
  public Bounds getBounds() {
    return new Bounds(minLat, maxLat, minLon, maxLon);
  }

  public void setBounds(Bounds bd) {
    this.minLat = bd.getMinlat();
    this.maxLat = bd.getMaxlat();
    this.minLon = bd.getMinlon();
    this.maxLon = bd.getMaxlon();
  }

  public String getSettingFile() {
    return this.SettingFile;
  }

  public void setSettingFile(String SettingFile) {
    this.SettingFile = SettingFile;
  }

  public String getAppDataPath() {
    return this.AppDataPath;
  }

  public void setAppDataPath(String AppDataPath) {
    this.AppDataPath = AppDataPath;
  }

  public Props(String SettingFile, String MapFile, String defaultMapFile, String AppDataPath, String OSM_API_ROOT_URL,
      String DatabseURL, BUILD_TYPE hasToBuild, Double defaultMinLon, Double defaultMaxLon, Double defaultMinLat,
      Double defaultMaxLat, Double minLon, Double maxLon, Double minLat, Double maxLat, Styles styles, Integer zoom,
      Integer zoomStep, URL downloadURL) {
    this.SettingFile = SettingFile;
    this.MapFile = MapFile;
    this.defaultMapFile = defaultMapFile;
    this.AppDataPath = AppDataPath;
    this.OSM_API_ROOT_URL = OSM_API_ROOT_URL;
    this.DatabseURL = DatabseURL;
    this.hasToBuild = hasToBuild;
    this.defaultMinLon = defaultMinLon;
    this.defaultMaxLon = defaultMaxLon;
    this.defaultMinLat = defaultMinLat;
    this.defaultMaxLat = defaultMaxLat;
    this.minLon = minLon;
    this.maxLon = maxLon;
    this.minLat = minLat;
    this.maxLat = maxLat;
    this.styles = styles;
    this.zoom = zoom;
    this.zoomStep = zoomStep;
    this.downloadURL = downloadURL;
  }

  public String getMapFile() {
    return this.MapFile;
  }

  public void setMapFile(String MapFile) {
    this.MapFile = MapFile;
  }

  public String getDefaultMapFile() {
    return this.defaultMapFile;
  }

  public void setDefaultMapFile(String defaultMapFile) {
    this.defaultMapFile = defaultMapFile;
  }

  public Integer getZoom() {
    return this.zoom;
  }

  public void setZoom(Integer zoom) {
    this.zoom = zoom;
  }

  public Integer getZoomStep() {
    return this.zoomStep;
  }

  public void setZoomStep(Integer zoomStep) {
    this.zoomStep = zoomStep;
  }

  public Props defaultMapFile(String defaultMapFile) {
    setDefaultMapFile(defaultMapFile);
    return this;
  }

  public Props AppDataPath(String AppDataPath) {
    setAppDataPath(AppDataPath);
    return this;
  }

  public Props zoom(Integer zoom) {
    setZoom(zoom);
    return this;
  }

  public Props zoomStep(Integer zoomStep) {
    setZoomStep(zoomStep);
    return this;
  }

  public Props downloadURL(URL downloadURL) {
    setDownloadURL(downloadURL);
    return this;
  }

  public String getOSM_API_ROOT_URL() {
    return this.OSM_API_ROOT_URL;
  }

  public void setOSM_API_ROOT_URL(String OSM_API_ROOT_URL) {
    this.OSM_API_ROOT_URL = OSM_API_ROOT_URL;
  }

  public String getDatabseURL() {
    return this.DatabseURL;
  }

  public void setDatabseURL(String DatabseURL) {
    this.DatabseURL = DatabseURL;
  }

  public BUILD_TYPE hasToBuild() {
    return this.hasToBuild;
  }

  public BUILD_TYPE getHasToBuild() {
    return this.hasToBuild;
  }

  public void setHasToBuild(BUILD_TYPE hasToBuild) {
    this.hasToBuild = hasToBuild;
  }

  public Double getDefaultMinLon() {
    return this.defaultMinLon;
  }

  public void setDefaultMinLon(Double defaultMinLon) {
    this.defaultMinLon = defaultMinLon;
  }

  public Double getDefaultMaxLon() {
    return this.defaultMaxLon;
  }

  public void setDefaultMaxLon(Double defaultMaxLon) {
    this.defaultMaxLon = defaultMaxLon;
  }

  public Double getDefaultMinLat() {
    return this.defaultMinLat;
  }

  public void setDefaultMinLat(Double defaultMinLat) {
    this.defaultMinLat = defaultMinLat;
  }

  public Double getDefaultMaxLat() {
    return this.defaultMaxLat;
  }

  public void setDefaultMaxLat(Double defaultMaxLat) {
    this.defaultMaxLat = defaultMaxLat;
  }

  public Double getMinLon() {
    return defaultValue(this.minLon, this.defaultMinLon);
  }

  public void setMinLon(Double minLon) {
    this.minLon = minLon;
  }

  public Double getMaxLon() {
    return defaultValue(this.maxLon, this.defaultMaxLon);
  }

  public void setMaxLon(Double maxLon) {
    this.maxLon = maxLon;
  }

  public Double getMinLat() {
    return defaultValue(this.minLat, this.defaultMinLat);
  }

  public void setMinLat(Double minLat) {
    this.minLat = minLat;
  }

  public Double getMaxLat() {
    return defaultValue(this.maxLat, this.defaultMaxLat);
  }

  public void setMaxLat(Double maxLat) {
    this.maxLat = maxLat;
  }

  public Styles getStyles() {
    return this.styles;
  }

  public void setStyles(Styles styles) {
    this.styles = styles;
  }

  public URL getDownloadURL() {
    return this.downloadURL;
  }

  public void setDownloadURL(URL url) {
    this.downloadURL = url;
  }

  public Props SettingFile(String SettingFile) {
    setSettingFile(SettingFile);
    return this;
  }

  public Props MapFile(String MapFile) {
    setMapFile(MapFile);
    return this;
  }

  public Props OSM_API_ROOT_URL(String OSM_API_ROOT_URL) {
    setOSM_API_ROOT_URL(OSM_API_ROOT_URL);
    return this;
  }

  public Props DatabseURL(String DatabseURL) {
    setDatabseURL(DatabseURL);
    return this;
  }

  public Props defaultMapFileString(String defaultMapFileString) {
    setDefaultMapFile(defaultMapFileString);
    return this;
  }

  public Props hasToBuild(BUILD_TYPE hasToBuild) {
    setHasToBuild(hasToBuild);
    return this;
  }

  public Props defaultMinLon(Double defaultMinLon) {
    setDefaultMinLon(defaultMinLon);
    return this;
  }

  public Props defaultMaxLon(Double defaultMaxLon) {
    setDefaultMaxLon(defaultMaxLon);
    return this;
  }

  public Props defaultMinLat(Double defaultMinLat) {
    setDefaultMinLat(defaultMinLat);
    return this;
  }

  public Props defaultMaxLat(Double defaultMaxLat) {
    setDefaultMaxLat(defaultMaxLat);
    return this;
  }

  public Props minLon(Double minLon) {
    setMinLon(minLon);
    return this;
  }

  public Props maxLon(Double maxLon) {
    setMaxLon(maxLon);
    return this;
  }

  public Props minLat(Double minLat) {
    setMinLat(minLat);
    return this;
  }

  public Props maxLat(Double maxLat) {
    setMaxLat(maxLat);
    return this;
  }

  public Props styles(Styles styles) {
    setStyles(styles);
    return this;
  }

  // <---------------------------- Private functions ---------------------------->
  private <T> T defaultValue(T valuePossiblyEmpty, T defaultValue) {
    if (valuePossiblyEmpty == null) {
      return defaultValue;
    }
    return valuePossiblyEmpty;
  }
  // <----------------------------- Object overrides ---------------------------->

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Props)) {
      return false;
    }
    Props props = (Props) o;
    return Objects.equals(SettingFile, props.SettingFile) && Objects.equals(MapFile, props.MapFile)
        && Objects.equals(OSM_API_ROOT_URL, props.OSM_API_ROOT_URL) && Objects.equals(DatabseURL, props.DatabseURL)
        && Objects.equals(defaultMapFile, props.defaultMapFile) && Objects.equals(hasToBuild, props.hasToBuild)
        && Objects.equals(defaultMinLon, props.defaultMinLon) && Objects.equals(defaultMaxLon, props.defaultMaxLon)
        && Objects.equals(defaultMinLat, props.defaultMinLat) && Objects.equals(defaultMaxLat, props.defaultMaxLat)
        && Objects.equals(minLon, props.minLon) && Objects.equals(maxLon, props.maxLon)
        && Objects.equals(minLat, props.minLat) && Objects.equals(maxLat, props.maxLat)
        && Objects.equals(styles, props.styles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(SettingFile, MapFile, OSM_API_ROOT_URL, DatabseURL, defaultMapFile, hasToBuild, defaultMinLon,
        defaultMaxLon, defaultMinLat, defaultMaxLat, minLon, maxLon, minLat, maxLat, styles);
  }

  @Override
  public String toString() {
    return "{" + " SettingFile='" + getSettingFile() + "'" + ", MapFile='" + getMapFile() + "'" + ", OSM_API_ROOT_URL='"
        + getOSM_API_ROOT_URL() + "'" + ", DatabseURL='" + getDatabseURL() + "'" + ", defaultMapFile='"
        + getDefaultMapFile() + "'" + ", hasToBuild='" + hasToBuild() + "'" + ", defaultMinLon='" + getDefaultMinLon()
        + "'" + ", defaultMaxLon='" + getDefaultMaxLon() + "'" + ", defaultMinLat='" + getDefaultMinLat() + "'"
        + ", defaultMaxLat='" + getDefaultMaxLat() + "'" + ", minLon='" + getMinLon() + "'" + ", maxLon='" + getMaxLon()
        + "'" + ", minLat='" + getMinLat() + "'" + ", maxLat='" + getMaxLat() + "'" + ", styles='" + getStyles() + "'"
        + "}";
  }

}
