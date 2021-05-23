package com.sevenmap.spinel.elements.colliders;

import com.sevenmap.spinel.elements.Item;
import com.sevenmap.spinel.math.Vector3f;

public abstract class Collider extends Item {
    protected Collider(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation);
        this.scale = scale;
    }
}
