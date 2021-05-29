package com.sevenmap.core.map;

import com.sevenmap.core.Loadable;
import com.sevenmap.core.Props;
import com.sevenmap.core.Props.BUILD_TYPE;
import com.sevenmap.data.parsers.MapParser;

public class Map extends Loadable {
  public Map(Props props) {
    super(props);
  }

  /**
   * run {@link #build()} if {@link #props}.hasToBuild is not
   * {@link BUILD_TYPE.NULL}. Then display the map from its default zoom level.
   */
  @Override
  public void load() {
    // =============================== map builder ================================
    // This is supposed to be done once, when the user load a new map.
    // Build a plainMap from a file.
    // ============================================================================
    build();

    // Here, there is supposed to be a map loaded in database whose setup are in
    // props.

    // ================================ map loader ================================
    // This is supposed to be done once, when the user load a new map.
    // ============================================================================
  }

  /**
   * TODO
   */
  public void update() {
    // TODO
  }

  /**
   * Build a map from {@code props.MapFile} if {@code props.hasToBuild} is true.
   * <br>
   * Set {@code props.hasToBuild} to false.
   */
  public void build() {
    if (props.hasToBuild().equals(Props.BUILD_TYPE.FROM_FILE) || props.hasToBuild().equals(Props.BUILD_TYPE.FROM_URL)) {

      // Create an OSM Map
      MapParser mapSource = MapParser.GenerateRightMapType(props);

      // Download new map
      mapSource.downloadMap();

      // Parse data
      mapSource.parse();

      // Build map
      mapSource.build();

      // Store this object to the database
      mapSource.store();
    }
  }

  // <--------------------------- Getter and setters --------------------------->

  public Props getProps() {
    return this.props;
  }

  public void setProps(Props props) {
    this.props = props;
  }

}
