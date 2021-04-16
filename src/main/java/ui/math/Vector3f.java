package ui.math;

/**
 * A three-dimensional mathematical vector.
 */
public class Vector3f {
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
     * Add two Vector3f objects.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 + vec2
     */
    public static Vector3f add(Vector3f vec1, Vector3f vec2) {
        return new Vector3f(
            vec1.getX() + vec2.getX(), 
            vec1.getY() + vec2.getY(), 
            vec1.getZ() + vec2.getZ());
    }

    /**
     * Substract two Vector3f objects.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 - vec2
     */
    public static Vector3f sub(Vector3f vec1, Vector3f vec2) {
        return new Vector3f(
            vec1.getX() - vec2.getX(), 
            vec1.getY() - vec2.getY(), 
            vec1.getZ() - vec2.getZ());
    }

    /**
     * Calculate the dot product between two vectors.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 . vec2
     */
    public static float dot(Vector3f vec1, Vector3f vec2) {
        return vec1.getX() * vec2.getX() + vec1.getY() * vec2.getY() + vec1.getZ() * vec2.getZ();
    }
    
    /**
     * Calculate the norm of a vector.
     * @param vector input vector
     * @return norm
     */
    public static float norm(Vector3f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
    }

    /**
     * Multiply two Vector3f objects.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 .* vec2
     */
    public static Vector3f multiply(Vector3f vec1, Vector3f vec2) {
		return new Vector3f(vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), vec1.getZ() * vec2.getZ());
	}
	
    /**
     * Divide a Vector3f by another one.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 ./ vec2
     */
	public static Vector3f divide(Vector3f vec1, Vector3f vec2) {
		return new Vector3f(vec1.getX() / vec2.getX(), vec1.getY() / vec2.getY(), vec1.getZ() / vec2.getZ());
	}

    /**
     * Get the normalized vector.
     * @param vector input vector
     * @return normalized vector
     */
    public static Vector3f normalize(Vector3f vector) {
        float length = Vector3f.norm(vector);
        return Vector3f.divide(vector, new Vector3f(length, length, length));
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
