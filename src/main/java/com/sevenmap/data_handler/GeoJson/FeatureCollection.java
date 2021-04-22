
package com.sevenmap.data_handler.GeoJson;

import java.util.List;
import java.util.Objects;

public class FeatureCollection {
  private String type;
  private List<Feature> features;

  public FeatureCollection() {
  }

  public FeatureCollection(String type, List<Feature> features) {
    this.type = type;
    this.features = features;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Feature> getFeatures() {
    return this.features;
  }

  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

  public FeatureCollection type(String type) {
    setType(type);
    return this;
  }

  public FeatureCollection features(List<Feature> features) {
    setFeatures(features);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FeatureCollection)) {
            return false;
        }
        FeatureCollection featureCollection = (FeatureCollection) o;
        return Objects.equals(type, featureCollection.type) && Objects.equals(features, featureCollection.features);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, features);
  }

  @Override
  public String toString() {
    return "{" +
      " type='" + getType() + "'" +
      ", features='" + getFeatures() + "'" +
      "}";
  }

}
