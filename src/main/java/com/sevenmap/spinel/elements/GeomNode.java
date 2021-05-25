package com.sevenmap.spinel.elements;

import com.sevenmap.exceptions.IncorrectChildTypeError;
import com.sevenmap.spinel.math.Vector3f;

public class GeomNode extends Node {
    protected Vector3f position;
    protected Vector3f rotation;

    public GeomNode(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
        name = this.getClass().getSimpleName();
    }

    public GeomNode(Vector3f position, Vector3f rotation, String name) {
        this.position = position;
        this.rotation = rotation;
        this.name = name;
    }

    /**
     * The renderer uses this method to distinguish a GeomNode from an Item.
     * <p>
     * This isn't the most elegant solution ever, so suggestions are welcome.
     * </p>
     * 
     * @return
     */
    public boolean hasMesh() {
        return false;
    }

    /**
     * Get the GeomNode's position.
     * 
     * @return position vector
     */
    public Vector3f getPos() {
        return position;
    }

    /**
     * Get the GeomNode's rotation.
     * 
     * @return rotation angle vector
     */
    public Vector3f getRot() {
        return rotation;
    }

    /**
     * Set the GeomNode's position.
     * 
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     * @param z coordinate on the Z axis
     */
    public void setPos(float x, float y, float z) {
        Vector3f delta = new Vector3f(x, y, z).sub(new Vector3f(position));
        position.set(x, y, z);
        hiddenChildren.forEach((Node cNode) -> {
            Vector3f current = ((GeomNode) cNode).getPos();
            Vector3f next = current.add(delta);
            ((GeomNode) cNode).setPos(next);
        });
        shownChildren.forEach((Node cNode) -> {
            Vector3f current = ((GeomNode) cNode).getPos();
            Vector3f next = current.add(delta);
            ((GeomNode) cNode).setPos(next);
        });
    }

    /**
     * Set the GeomNode's position.
     * 
     * @param vect the position vector to be read
     */
    public void setPos(Vector3f vect) {
        setPos(vect.getX(), vect.getY(), vect.getZ());
    }

    /**
     * Set the GeomNode's rotation.
     * 
     * @param l coordinate on the X axis
     * @param m coordinate on the Y axis
     * @param n coordinate on the Z axis
     */
    public void setRot(float l, float m, float n) {
        Vector3f delta = new Vector3f(l, m, n).sub(new Vector3f(rotation));
        rotation.set(l, m, n);
        hiddenChildren.forEach((Node cNode) -> {
            Vector3f current = ((GeomNode) cNode).getRot();
            Vector3f next = current.add(delta);
            ((GeomNode) cNode).setRot(next);
        });
        shownChildren.forEach((Node cNode) -> {
            Vector3f current = ((GeomNode) cNode).getRot();
            Vector3f next = current.add(delta);
            ((GeomNode) cNode).setRot(next);
        });
    }

    /**
     * Set the GeomNode's rotation.
     * 
     * @param vect the rotation vector to be read
     */
    public void setRot(Vector3f vect) {
        setRot(vect.getX(), vect.getY(), vect.getZ());
    }
    
    /**
     * Project the normal vector e_r into the 
     * cartesian scene-specific frame of reference.
     * @return normal vector e_r
     */
    public Vector3f getReferenceX() {
        double theta = Math.toRadians(rotation.getY());
        double phi = Math.toRadians(rotation.getX());
        return new Vector3f(
            (float) - (Math.sin(theta) * Math.cos(phi)),
            (float) Math.sin(phi),
            (float) - (Math.cos(theta) * Math.cos(phi))
        );
    }

    /**
     * Project the tangent vector e_phi into the 
     * cartesian scene-specific frame of reference.
     * Note: Phi is the pitch angle
     * @return tangent vector
     */
    public Vector3f getReferenceY() {
        double phi = Math.toRadians(rotation.getX());
        double beta = Math.toRadians(rotation.getZ());
        return new Vector3f(
            (float) - Math.sin(beta),
            (float) (Math.cos(phi) * Math.cos(beta)),
            (float) Math.sin(phi)
        );
    }

    /**
     * Project the tangent vector e_theta into the 
     * cartesian scene-specific frame of reference.
     * Note: Theta is the yaw angle
     * @return tangent vector
     */
    public Vector3f getReferenceZ() {
        double theta = Math.toRadians(rotation.getY());
        double beta = Math.toRadians(rotation.getZ());
        return new Vector3f(
            (float) - (Math.cos(theta) * Math.cos(beta)),
            (float) - Math.sin(beta),
            (float) (Math.sin(theta) * Math.cos(beta))
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compatibilityCheck(Node child) {
        if (!(child instanceof GeomNode)) {
            throw new IncorrectChildTypeError("GeomNode element can only receive children of types GeomNode or lower.");
        }
    }
}
