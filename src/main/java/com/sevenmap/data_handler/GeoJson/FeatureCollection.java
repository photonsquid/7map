
package com.sevenmap.data_handler.GeoJson;

import java.util.ArrayList;
import java.util.Objects;

public class FeatureCollection {
  private Type type;
  private ArrayList<Feature> features;

  public FeatureCollection() {
  }

  public FeatureCollection(Type type, ArrayList<Feature> features) {
    this.type = type;
    this.features = features;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public ArrayList<Feature> getFeatures() {
    return this.features;
  }

  public void setFeatures(ArrayList<Feature> features) {
    this.features = features;
  }

  public FeatureCollection type(Type type) {
    setType(type);
    return this;
  }

  public FeatureCollection features(ArrayList<Feature> features) {
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
