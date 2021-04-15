package ui.math;

public class Vector2f {
    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // getters and setters
    
    public float getX() {return x;}
    public float getY() {return y;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
