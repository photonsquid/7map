package com.sevenmap.ui.elements;

import com.sevenmap.ui.math.Vector3f;

public class Node extends RootNode {
    protected RootNode parent;
    protected Vector3f position;
    protected Vector3f rotation;
    
    public Node(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * The renderer uses this method to distinguish a Node from an
     * Item.
     * <p> 
     * This isn't the most elegant solution ever, so suggestions are
     * welcome.
     * </p>
     * @return
     */
    public boolean hasMesh() {
        return false;
    }

    /**
     * Reparent an Node to another Node element.
     * @param parent the parent Node
     */
    public void setParent(RootNode parent) {
        // remove this object from the old parent's children list
        if (this.parent != null) {
            this.parent.delChild(this);
        }
        // add this object to the new parent's children list
        this.parent = parent;
        this.parent.addChild(this);
    }

    /**
     * Get an Node's parent Node.
     * @return the parent Node
     */
    public RootNode getParent() {
        return parent;
    }

    /**
     * Get the CartesianNode's position.
     * @return position vector
     */
    public Vector3f getPos() {
        return position;
    }

    /**
     * Get the CartesianNode's rotation.
     * @return rotation angle vector
     */
    public Vector3f getRot() {
        return rotation;
    }

    /**
     * Set the CartesianNode's position.
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     * @param z coordinate on the Z axis
     */
    public void setPos(float x, float y, float z) {
        Vector3f delta = new Vector3f(x, y, z).sub(new Vector3f(position));
        position.set(x, y, z);
        children.forEach((Node cNode) -> {
            Vector3f current = cNode.getPos();
            Vector3f next = current.add(delta);
            cNode.setPos(next);
        });
    }

    /**
     * Set the CartesianNode's position.
     * @param vect the position vector to be read
     */
    public void setPos(Vector3f vect) {
        setPos(vect.getX(), vect.getY(), vect.getZ());
    }

    /**
     * Set the CartesianNode's rotation.
     * @param l coordinate on the X axis
     * @param m coordinate on the Y axis
     * @param n coordinate on the Z axis
     */
    public void setRot(float l, float m, float n) {
        Vector3f delta = new Vector3f(l, m, n).sub(new Vector3f(rotation));
        rotation.set(l, m, n);
        children.forEach((Node cNode) -> {
            Vector3f current = cNode.getRot();
            Vector3f next = current.add(delta);
            cNode.setRot(next);
        });
    }

    /**
     * Set the CartesianNode's rotation.
     * @param vect the rotation vector to be read
     */
    public void setRot(Vector3f vect) {
        setRot(vect.getX(), vect.getY(), vect.getZ());
    }

}
