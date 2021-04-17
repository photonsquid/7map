package com.sevenmap.ui.elements;

import com.sevenmap.ui.gfx.Mesh;
import com.sevenmap.ui.math.Vector3f;

/**
 * A 3D object which can be rendered
 */
public class Item {
    protected Vector3f position, rotation, scale;
    protected Mesh mesh;

    public Item (Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Item (Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this(position, rotation);
        this.scale = scale;
        this.mesh = mesh;
    }

    

    // getters and setters

    /**
     * Get the Item's position.
     * @return position vector
     */
    public Vector3f getPos() {
        return position;
    }

    /**
     * Get the Item's rotation.
     * @return rotation angle vector
     */
    public Vector3f getRot() {
        return rotation;
    }

    /**
     * Get the Item's scale.
     * @return scale vector
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * Get the mesh associated with the Item.
     */
    public Mesh getMesh() {
        return mesh;
    }

    /**
     * Set the Item's position.
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     * @param z coordinate on the Z axis
     */
    public void setPos(float x, float y, float z) {
        position.set(x, y, z);
    }

    /**
     * Set the Item's rotation.
     * @param l coordinate on the X axis
     * @param m coordinate on the Y axis
     * @param n coordinate on the Z axis
     */
    public void setRot(float l, float m, float n) {
        rotation.set(l, m, n);
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

    public void destroy() {
        mesh.destroy();
    }
}
