package com.sevenmap.ui.math;

/**
 * A two-dimensional mathematical vector.
 */
public class Vector2f implements Vector<Vector2f> {
    private float x, y;

    /**
     * Generate a two-dimensional vector object.
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Generate a two-dimensional vector object.
     * @param vect vector to be read
     */
    public Vector2f(Vector2f vect) {
        this.x = vect.getX();
        this.y = vect.getY();
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
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public Vector2f add(Vector2f vec) {
        return new Vector2f(
            x + vec.getX(), 
            y + vec.getY());
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public Vector2f sub(Vector2f vec) {
        return new Vector2f(
            x - vec.getX(), 
            y - vec.getY());
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public float dot(Vector2f vec) {
        return x * vec.getX() + y * vec.getY();
    }
    
    /**
     * {@inheritDoc}
     */
    public float norm() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
    public Vector2f multiply(Vector2f vec) {
		return new Vector2f(x * vec.getX(), y * vec.getY());
	}
	
    /**
     * {@inheritDoc}
     * @param value {@inheritDoc}
     * @return {@inheritDoc}
     */
    public Vector2f multiply(float value) {
        return new Vector2f(x * value, y * value);
    }

    /**
     * {@inheritDoc}
     * @param vec {@inheritDoc}
     */
	public Vector2f divide(Vector2f vec) {
		return new Vector2f(x / vec.getX(), y / vec.getY());
	}

    /**
     * {@inheritDoc}
     * @param value {@inheritDoc}
     */
    public Vector2f divide(float value) {
        return new Vector2f(x / value, y / value);
    }

    /**
     * {@inheritDoc}
     */
    public Vector2f normalize() {
        float length = this.norm();
        return this.divide(new Vector2f(length, length));
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
