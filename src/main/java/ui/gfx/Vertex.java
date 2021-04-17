package ui.gfx;

import ui.math.Vector2f;
import ui.math.Vector3f;
import ui.utils.Color;

public class Vertex {
    private Vector3f pos;
    private Vector3f color;
    private Vector2f textureCoord;
    public static final int SIZE = 3;

    public Vertex(Vector3f position, Vector3f color, Vector2f textureCoord) {
        this.pos = position;
        this.color = color;
        this.textureCoord = textureCoord;
    }
      public Vertex(Vector3f position, Color color, Vector2f textureCoord) {
        this.pos = position;
        this.color = color.toVector3f();
        this.textureCoord = textureCoord;
    }

    public Vector3f getPos() {return pos;}

    public Vector3f getColor() {return color;}

    public Vector2f getTexCoord() {return textureCoord;}
}
