package com.sevenmap.data.parsers.osm.Structure;

import com.sevenmap.data.parsers.osm.Structure.Tag.Tag;

public interface HasTag {

  public Tag findTag(String key);

}
