package ui.math;

/**
 * A two-dimensional mathematical vector.
 */
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
    public void set(Vector2f vector) {
        x = vector.getX();
        y = vector.getY();
    }

    // other methods

    /**
     * Add two Vector2f objects.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 + vec2
     */
    public static Vector2f add(Vector2f vec1, Vector2f vec2) {
        return new Vector2f(
            vec1.getX() + vec2.getX(), 
            vec1.getY() + vec2.getY());
    }

    /**
     * Substract two Vector2f objects.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 - vec2
     */
    public static Vector2f sub(Vector2f vec1, Vector2f vec2) {
        return new Vector2f(
            vec1.getX() - vec2.getX(), 
            vec1.getY() - vec2.getY());
    }

    /**
     * Calculate the dot product between two vectors.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 . vec2
     */
    public static float dot(Vector2f vec1, Vector2f vec2) {
        return vec1.getX() * vec2.getX() + vec1.getY() * vec2.getY();
    }
    
    /**
     * Calculate the norm of a vector.
     * @param vector input vector
     * @return norm
     */
    public static float norm(Vector2f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }

    /**
     * Multiply two Vector2f objects.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 .* vec2
     */
    public static Vector2f multiply(Vector2f vec1, Vector2f vec2) {
		return new Vector2f(vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY());
	}
	
    /**
     * Divide a Vector2f by another one.
     * @param vec1 first vector
     * @param vec2 second vector
     * @return vec1 ./ vec2
     */
	public static Vector2f divide(Vector2f vec1, Vector2f vec2) {
		return new Vector2f(vec1.getX() / vec2.getX(), vec1.getY() / vec2.getY());
	}

    /**
     * Get the normalized vector.
     * @param vector input vector
     * @return normalized vector
     */
    public static Vector2f normalize(Vector2f vector) {
        float length = Vector2f.norm(vector);
        return Vector2f.divide(vector, new Vector2f(length, length));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
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
        Vector2f other = (Vector2f) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        return true;
    }
    
}
