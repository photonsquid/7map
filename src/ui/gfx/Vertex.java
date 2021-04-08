package ui.gfx;

import ui.math.Vector3f;

public class Vertex {
    /**The position of the vertex */
    private Vector3f pos;
    public static final int SIZE = 3;

    public Vertex(Vector3f position) {
        pos = position;
    }

    public Vector3f getPos() {return pos;}
}
