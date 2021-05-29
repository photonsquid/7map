package com.sevenmap.data.parsers.osm.Structure.Metadata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.Annotations.XMLClass;

@XMLClass(isXMLAttribute = false)
public class Metadata {
  @XMLAttribute
  private String user;
  @XMLAttribute
  private long uid;
  @XMLAttribute
  private Boolean visible;
  @XMLAttribute
  private long version;
  @XMLAttribute
  private long changeset;
  @XMLAttribute
  private Date timestamp;

  private static final String defaultDate = "1900-01-01T00:00:00Z";

  public Metadata() {
    this.user = "";
    this.uid = 1;
    this.visible = false;
    this.version = 1;
    this.changeset = 1;
    this.timestamp = parseDate(defaultDate);
  }

  public Metadata(String user, long uid, Boolean visible, long version, long changeset, Date timestamp) {
    this.user = user;
    this.uid = uid;
    this.visible = visible;
    this.version = version;
    this.changeset = changeset;
    this.timestamp = timestamp;
  }

  public Metadata(String user, long uid, Boolean visible, long version, long changeset, String timestamp) {
    this.user = user;
    this.uid = uid;
    this.visible = visible;
    this.version = version;
    this.changeset = changeset;
    this.timestamp = parseDate(timestamp);
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public long getUid() {
    return this.uid;
  }

  public void setUid(long uid) {
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

  public long getVersion() {
    return this.version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public long getChangeset() {
    return this.changeset;
  }

  public void setChangeset(long changeset) {
    this.changeset = changeset;
  }

  public Date getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = parseDate(timestamp);
  }

  public Metadata user(String user) {
    setUser(user);
    return this;
  }

  public Metadata uid(long uid) {
    setUid(uid);
    return this;
  }

  public Metadata visible(Boolean visible) {
    setVisible(visible);
    return this;
  }

  public Metadata version(long version) {
    setVersion(version);
    return this;
  }

  public Metadata changeset(long changeset) {
    setChangeset(changeset);
    return this;
  }

  public Metadata timestamp(Date timestamp) {
    setTimestamp(timestamp);
    return this;
  }

  public static Date parseDate(String timestamp) {
    DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // TODO: handle other formats than T...Z
    // cf https://www.w3.org/TR/NOTE-datetime
    timestamp = timestamp.replace('T', ' ');
    timestamp = timestamp.replace("Z", "");
    try {
      return dt.parse(timestamp);
    } catch (ParseException e) {
      // TODO: handleError
      e.printStackTrace();
      return new Date();
    }
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
