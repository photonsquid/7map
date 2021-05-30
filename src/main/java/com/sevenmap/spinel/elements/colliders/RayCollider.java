package com.sevenmap.spinel.elements.colliders;

import com.sevenmap.spinel.math.Vector3f;

/**
 * Unfinished
 */
public class RayCollider extends Collider {
    private Vector3f vector;

    /**
     * Create a new RayCollider object with a default name.
     * 
     * @param position the ray collider's position
     * @param vector   the ray collider's orientation vector
     */
    public RayCollider(Vector3f position, Vector3f vector) {
        super(position, vector); // WARNING vector should be rotation instead
    }

    /**
     * Create a new RayCollider object with a chosen name.
     * 
     * @param position the ray collider's position
     * @param vector   the ray collider's orientation vector
     * @param name     the given name
     */
    public RayCollider(Vector3f position, Vector3f vector, String name) {
        this(position, vector);
        this.name = this.getClass().getSimpleName();
    }

    @Override
    public void update() {
        // TODO : write update method
    }
}
