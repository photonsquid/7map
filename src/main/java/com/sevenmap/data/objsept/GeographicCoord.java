package com.sevenmap.data.objsept;

import java.util.Objects;

public class GeographicCoord {
  private Double lat;
  private Double lon;

  public GeographicCoord() {
  }

  public GeographicCoord(Double lat, Double lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public Double getLat() {
    return this.lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLon() {
    return this.lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public GeographicCoord lat(Double lat) {
    setLat(lat);
    return this;
  }

  public GeographicCoord lon(Double lon) {
    setLon(lon);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof GeographicCoord)) {
      return false;
    }
    GeographicCoord geographicCoord = (GeographicCoord) o;
    return Objects.equals(lat, geographicCoord.lat) && Objects.equals(lon, geographicCoord.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lat, lon);
  }

  @Override
  public String toString() {
    return "{" + " lat='" + getLat() + "'" + ", lon='" + getLon() + "'" + "}";
  }

}
