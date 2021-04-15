package ui.math;

public class Vector4f {
    private float w, x, y, z;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    // getters and setters
    
    public float getX() {return x;}
    public float getY() {return y;}
    public float getZ() {return z;}
    public float getW() {return w;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void setZ(float z) {this.z = z;}
    public void setW(float w) {this.w = w;}
    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
