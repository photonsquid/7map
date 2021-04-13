package ui.math;

public class Vector3f {
    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // getters and setters
    
    public float getX() {return x;}
    public float getY() {return y;}
    public float getZ() {return z;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void setZ(float z) {this.z = z;}
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
