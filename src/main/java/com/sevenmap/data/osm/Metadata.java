package com.sevenmap.data.osm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Metadata {
  private String user;
  private Integer uid;
  private Boolean visible;
  private Integer version;
  private Integer changeset;
  private Date timestamp;

  public Metadata() {
    this.user = "";
    this.uid = 1;
    this.visible = false;
    this.version = 1;
    this.changeset = 1;
    DateFormat dt = new SimpleDateFormat("dd-MM-yyyy:HH-mm-ss");
    try {
      this.timestamp = dt.parse("01-01-1900:00-00-00");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public Metadata(String user, Integer uid, Boolean visible, Integer version, Integer changeset, Date timestamp) {
    this.user = user;
    this.uid = uid;
    this.visible = visible;
    this.version = version;
    this.changeset = changeset;
    this.timestamp = timestamp;
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Integer getUid() {
    return this.uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public Boolean isVisible() {
    return this.visible;
  }

  public Boolean getVisible() {
    return this.visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getChangeset() {
    return this.changeset;
  }

  public void setChangeset(Integer changeset) {
    this.changeset = changeset;
  }

  public Date getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Metadata user(String user) {
    setUser(user);
    return this;
  }

  public Metadata uid(Integer uid) {
    setUid(uid);
    return this;
  }

  public Metadata visible(Boolean visible) {
    setVisible(visible);
    return this;
  }

  public Metadata version(Integer version) {
    setVersion(version);
    return this;
  }

  public Metadata changeset(Integer changeset) {
    setChangeset(changeset);
    return this;
  }

  public Metadata timestamp(Date timestamp) {
    setTimestamp(timestamp);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Metadata)) {
      return false;
    }
    Metadata metadata = (Metadata) o;
    return Objects.equals(user, metadata.user) && Objects.equals(uid, metadata.uid)
        && Objects.equals(visible, metadata.visible) && Objects.equals(version, metadata.version)
        && Objects.equals(changeset, metadata.changeset) && Objects.equals(timestamp, metadata.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, uid, visible, version, changeset, timestamp);
  }

  @Override
  public String toString() {
    return "{" + " user='" + getUser() + "'" + ", uid='" + getUid() + "'" + ", visible='" + isVisible() + "'"
        + ", version='" + getVersion() + "'" + ", changeset='" + getChangeset() + "'" + ", timestamp='" + getTimestamp()
        + "'" + "}";
  }

}
