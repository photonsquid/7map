package com.sevenmap.spinel.elements.colliders;

import com.sevenmap.spinel.elements.Item;
import com.sevenmap.spinel.gfx.Mesh;
import com.sevenmap.spinel.gfx.Vertex;
import com.sevenmap.spinel.math.Vector3f;
import com.sevenmap.spinel.utils.Color;

public class PlaneCollider extends Collider {
    private static float size = 40;
    private Vector3f[] frame;

    /**
     * Create a new PlaneCollider object with a default name.
     * @param position the collider's position
     * @param rotation the collider's rotation
     */
    public PlaneCollider(Vector3f position, Vector3f rotation) {
        super(position, rotation);
        frame = getFrame();
        Vector3f a, b, c, d;
        a = new Vector3f(frame[0].multiply(size).add(frame[1].multiply(size)));
        b = new Vector3f(frame[0].multiply(-size).add(frame[1].multiply(size)));
        c = new Vector3f(frame[0].multiply(-size).add(frame[1].multiply(-size)));
        d = new Vector3f(frame[0].multiply(size).add(frame[1].multiply(-size)));
        Mesh debugMesh = new Mesh(
            new Vertex[]{
                new Vertex(a, new Color(120, 120, 120)),
                new Vertex(b, new Color(120, 120, 120)),
                new Vertex(c, new Color(120, 120, 120)),
                new Vertex(d, new Color(120, 120, 120))
            },
            new int[]{
                0, 1, 2,
                0, 2, 3
            }
        );
        debugObject = new Item(
            new Vector3f(0, 0, 0),
            new Vector3f(0, 0, 0),
            new Vector3f(1, 1, 1),
            debugMesh,
            String.format("DebugObject@%s", this.getID())
        );
        debugObject.setParent(this);
        if (!debug) {debugObject.hide();}
    }

    /**
     * Create a new PlaneCollider object with a chosen name.
     * @param position the collider's position
     * @param rotation the collider's rotation
     * @param name the given name
     */
    public PlaneCollider(Vector3f position, Vector3f rotation, String name) {
        this(position, rotation);
        this.name = this.getClass().getSimpleName();
    }

    @Override
    public void update() {
        // TODO : write update method
    }

    public Vector3f[] getFrame() {
        double theta = Math.toRadians(rotation.getY());
        double phi = Math.toRadians(rotation.getX());
        double beta = Math.toRadians(rotation.getZ());
        return new Vector3f[] {
            new Vector3f(
            (float) - (Math.sin(theta) * Math.cos(phi)),
            (float) Math.sin(phi),
            (float) - (Math.cos(theta) * Math.cos(phi))),
            new Vector3f(
            (float) - (Math.cos(theta) * Math.cos(beta)),
            (float) - Math.sin(beta),
            (float) (Math.sin(theta) * Math.cos(beta)))
        };
    }
}
