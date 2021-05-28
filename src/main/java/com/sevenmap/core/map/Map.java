package com.sevenmap.core.map;

import java.io.File;

import com.sevenmap.core.Loadable;
import com.sevenmap.core.Props;
import com.sevenmap.data.optiObj.LightObj;
import com.sevenmap.data.parsers.osm.OSM;
import com.sevenmap.data.parsers.osm.Elements.Bounds.Bounds;
import com.sevenmap.spinel.math.Vector3f;

public class Map extends Loadable {
  private Props props;

  public void load(Props props) {
    if (props.hasToBuild()) {
      // ========================== map loader ==========================
      // This is supposed to be done once, when the user load a new map.
      // ================================================================

      // Create OSM Map
      File n7Map = new File(props.getMapFile());
      OSM OSMMap = new OSM(parseBounds(), n7Map);

      // Download new map
      OSMMap.downloadMap();

      // Parse data
      OSMMap.parse();

      // Convert into optimized files
      LightObj ltObj = new LightObj(OSMMap, props);
      ltObj.parse();

      // Store this object to the database
      ltObj.store();
    }

  }

  public static Vector3f GeoCoord2SpinelCoord(GeographicCoord coords) {
    Double x_proj = Math.cos(0.0) * (coords.getLat());
    Double y_proj = coords.getLat();

    Float x = x_proj.floatValue();
    Float y = y_proj.floatValue();
    Float z = 0f;

    return new Vector3f(x, y, z);
  }

  public static GeographicCoord SpinelCoord2GeoCoord(Vector3f vect) {
    Double lat = (double) vect.getX();
    Double lon = (double) vect.getY();
    return new GeographicCoord(lat, lon);
  }

  private Bounds parseBounds() {

    Double minLon = props.getMinLon();
    Double maxLon = props.getMaxLon();
    Double minLat = props.getMinLat();
    Double maxLat = props.getMaxLat();

    // Return them
    return new Bounds(minLon, maxLon, minLat, maxLat);
  }

}
