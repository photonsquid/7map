package com.sevenmap.ui.elements;

import com.sevenmap.ui.math.Matrix4f;
import com.sevenmap.ui.math.Vector3f;

/**
 * Documentation required @l3alr0g
 */
public class Camera extends GeomNode {
    private float fov = 70.0f;
    private float[] nearfar = {0.15f, 10_000.0f};
    private Matrix4f projector;


    public Camera(Vector3f position, Vector3f rotation, float aspect) {
        super(position, rotation, "Camera");
        projector = Matrix4f.project(aspect, fov, nearfar[0], nearfar[1]); // (float) width / (float) height
    }
    
    public Matrix4f getProjector() {
        return projector;
    }

    public void setProjector(Matrix4f projector) {
        this.projector = projector;
    }

    public Vector3f getNormal() {
        double theta = Math.toRadians(rotation.getY());
        double phi = Math.toRadians(rotation.getX());
        return new Vector3f(
            (float) - (Math.sin(theta) * Math.cos(phi)),
            (float) Math.sin(phi),
            (float) - (Math.cos(theta) * Math.cos(phi))
        );
    }

    public Vector3f getTangent() {
        double theta = Math.toRadians(rotation.getY());
        double beta = Math.toRadians(rotation.getZ());
        return new Vector3f(
            (float) - (Math.cos(theta) * Math.cos(beta)),
            (float) - Math.sin(beta),
            (float) (Math.sin(theta) * Math.cos(beta))
        );
    }
}
