package com.sevenmap.ui.elements;

import com.sevenmap.ui.gfx.Mesh;
import com.sevenmap.ui.math.Vector3f;

/**
 * A 3D object which can be rendered
 */
public class Item extends Node {

    protected Vector3f scale;
    protected Mesh mesh;

    public Item (Vector3f position, Vector3f rotation) {
        super(position, rotation);
    }

    public Item (Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this(position, rotation);
        this.scale = scale;
        this.mesh = mesh;
    }

    // getters and setters

    /**
     * Get the Item's scale.
     * @return scale vector
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * Get the mesh associated to the Item.
     */
    public Mesh getMesh() {
        return mesh;
    }

    /**
     * Set the Item's scale.
     * @param sx scale on the X axis
     * @param sy scale on the Y axis
     * @param sz scale on the Z axis
     */
    public void setScale(float sx, float sy, float sz) {
        scale.set(sx, sy, sz);
    }
    
    // other methods

    @Override
    public boolean hasMesh() {
        return true;
    }

    @Override
    public void destroy() {
        mesh.destroy();
    }
}
