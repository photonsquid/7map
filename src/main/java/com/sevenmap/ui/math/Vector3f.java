package com.sevenmap.ui.math;

/**
 * A three-dimensional mathematical vector.
 */
public class Vector3f implements Vector<Vector3f> {
    private float x, y, z;

    /**
     * Generate a three-dimensional vector object.
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     * @param z coordinate on the Z axis
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Generate a three-dimensional vector object.
     * @param vect vector to be read
     */
    public Vector3f(Vector3f vect) {
        this.x = vect.getX();
        this.y = vect.getY();
        this.z = vect.getZ();
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
    public void set(Vector3f vector) {
        x = vector.getX();
        y = vector.getY();
        z = vector.getZ();
    }

    // other methods

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public Vector3f add(Vector3f vec) {
        return new Vector3f(
            x + vec.getX(), 
            y + vec.getY(), 
            z + vec.getZ());
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public Vector3f sub(Vector3f vec) {
        return new Vector3f(
            x - vec.getX(), 
            y - vec.getY(), 
            z - vec.getZ());
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public float dot(Vector3f vec) {
        return x * vec.getX() + y * vec.getY() + z * vec.getZ();
    }
    
    /**
     * {@inheritDoc}
     */
    public float norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public Vector3f multiply(Vector3f vec) {
		return new Vector3f(x * vec.getX(), y * vec.getY(), z * vec.getZ());
	}
	
    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
	public Vector3f divide(Vector3f vec) {
		return new Vector3f(x / vec.getX(), y / vec.getY(), z / vec.getZ());
	}

    /**
     * {@inheritDoc}
     */
    public Vector3f normalize() {
        float length = this.norm();
        return this.divide(new Vector3f(length, length, length));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        result = prime * result + Float.floatToIntBits(z);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector3f other = (Vector3f) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
            return false;
        return true;
    }
}
