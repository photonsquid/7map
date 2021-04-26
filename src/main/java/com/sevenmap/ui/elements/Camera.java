package com.sevenmap.ui.elements;

import com.sevenmap.ui.math.Matrix4f;
import com.sevenmap.ui.math.Vector3f;

public class Camera extends Node {
    private float fov = 70.0f;
    private float[] nearfar = {0.15f, 10_000.0f};
    private Matrix4f projector;

    public Camera(Vector3f position, Vector3f rotation, float aspect) {
        super(position, rotation);
        projector = Matrix4f.project(aspect, fov, nearfar[0], nearfar[1]); // (float) width / (float) height
    }
    
    public Matrix4f getProjector() {
        return projector;
    }

    public void setProjector(Matrix4f projector) {
        this.projector = projector;
    }
}
