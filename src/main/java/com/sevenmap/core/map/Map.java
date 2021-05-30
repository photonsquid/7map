package com.sevenmap.core.map;

import com.sevenmap.core.Loadable;
import com.sevenmap.core.Props;
import com.sevenmap.core.Props.BUILD_TYPE;
import com.sevenmap.data.objsept.PlainMap;
import com.sevenmap.data.parsers.MapParser;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.geom.Item;

public class Map extends Loadable {
  private PlainMap<Long, Item> plainMap;

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

    // =============================== map displayer ==============================
    // This is not supposed to be here...
    // ============================================================================

  }

  public void reload() {
    unload();
    load();
  }

  public void unload() {
    Engine engine = Engine.getInstance();
    for (PlainMap.Entry<Long, Item> entry : this.plainMap.entrySet()) {
      Item road = entry.getValue();
      road.setParent(null);
    }
    this.plainMap.clear();
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

      // Cheat
      Engine engine = Engine.getInstance();
      this.plainMap = mapSource.getGeneratedMap();
      for (PlainMap.Entry<Long, Item> entry : this.plainMap.entrySet()) {
        Item road = entry.getValue();
        road.setParent(engine.getSceneRoot());
      }
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
