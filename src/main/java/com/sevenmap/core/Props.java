package com.sevenmap.core;

import java.util.Objects;

public class Props {
  private String SettingFile;
  private String MapFile;
  private String OSM_API_ROOT_URL;
  private String DatabseURL;
  private String defaultMapFileString;
  private Boolean hasToBuild;
  // Default bounds (arround ENSEEIHT school)
  private Double defaultMinLon = 1.45338;
  private Double defaultMaxLon = 1.45760;
  private Double defaultMinLat = 43.60116;
  private Double defaultMaxLat = 43.60297;

  private Double minLon;
  private Double maxLon;
  private Double minLat;
  private Double maxLat;

  public Double getMinLon() {
    if (minLon != null) {
      return this.minLon;
    } else {
      return this.defaultMinLon;
    }
  }

  public void setMinLon(Double minLon) {
    this.minLon = minLon;
  }

  public Double getMaxLon() {
    if (this.maxLon != null) {
      return this.maxLon;
    } else {
      return defaultMaxLon;
    }
  }

  public void setMaxLon(Double maxLon) {
    this.maxLon = maxLon;
  }

  public Double getMinLat() {
    if (this.minLat != null) {
      return this.minLat;
    } else {
      return defaultMinLat;
    }
  }

  public void setMinLat(Double minLat) {
    this.minLat = minLat;
  }

  public Double getMaxLat() {
    if (this.maxLat != null) {
      return this.maxLat;
    } else {
      return defaultMaxLat;
    }
  }

  public void setMaxLat(Double maxLat) {
    this.maxLat = maxLat;
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

  public Boolean isHasToBuild() {
    return this.hasToBuild;
  }

  public Boolean hasToBuild() {
    return this.hasToBuild;
  }

  public Boolean getHasToBuild() {
    return this.hasToBuild;
  }

  public void setHasToBuild(Boolean hasToBuild) {
    this.hasToBuild = hasToBuild;
  }

  public String getDefaultMapFileString() {
    return this.defaultMapFileString;
  }

  public void setDefaultMapFileString(String defaultMapFileString) {
    this.defaultMapFileString = defaultMapFileString;
  }

  public Props() {
    this.defaultMapFileString = "src/main/resources/maps/osm/n7.osm";
  }

  public Props(String SettingFile, String MapFile, String OSM_API_ROOT_URL, String DatabseURL) {
    super();
    this.SettingFile = SettingFile;
    this.MapFile = MapFile;
    this.OSM_API_ROOT_URL = OSM_API_ROOT_URL;
    this.DatabseURL = DatabseURL;
  }

  public String getSettingFile() {
    return this.SettingFile;
  }

  public void setSettingFile(String SettingFile) {
    this.SettingFile = SettingFile;
  }

  public String getMapFile() {
    return this.MapFile;
  }

  public void setMapFile(String MapFile) {
    this.MapFile = MapFile;
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

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Props)) {
      return false;
    }
    Props props = (Props) o;
    return Objects.equals(SettingFile, props.SettingFile) && Objects.equals(MapFile, props.MapFile)
        && Objects.equals(OSM_API_ROOT_URL, props.OSM_API_ROOT_URL) && Objects.equals(DatabseURL, props.DatabseURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(SettingFile, MapFile, OSM_API_ROOT_URL, DatabseURL);
  }

  @Override
  public String toString() {
    return "{" + " SettingFile='" + getSettingFile() + "'" + ", MapFile='" + getMapFile() + "'" + ", OSM_API_ROOT_URL='"
        + getOSM_API_ROOT_URL() + "'" + ", DatabseURL='" + getDatabseURL() + "'" + "}";
  }

}
