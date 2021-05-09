package com.sevenmap.ui.elements.colliders;

import com.sevenmap.ui.elements.Item;
import com.sevenmap.ui.math.Vector3f;

public abstract class Collider extends Item {
    protected Collider(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation);
        this.scale = scale;
    }
}
