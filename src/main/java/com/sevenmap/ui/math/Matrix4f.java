package com.sevenmap.ui.math;

import java.util.Arrays;

/**
 * A 2d matrix of 4 by 4 elements stored as a 1d object.
 */
public class Matrix4f {
    private float[] elements = new float[SIZE * SIZE]; // 2d matrix stored in a 1d system
    public static final int SIZE = 4;

    // getters and setters

    /**
     * Retrieve data at coordinates (a, b).
     * @param a index
     * @param b index
     * @return data
     */
    public float get(int a, int b) {
        return elements[b * SIZE + a];
    }

    /**
     * Set data at coordinates (a, b).
     * @param a column's index 
     * @param b line's index
     * @param val value
     */
    public void set(int a, int b, float val) {
        elements[b * SIZE + a] = val;
    }

    /**
     * Retrieve the stored raw data.
     * @return data array
     */
    public float[] getContent() {
        return elements;
    }

    /**
     * Set all elements inside the matrix at once.
     * @param content element float array
     */
    public void setContent(float[] content) {
        elements = content;
    }

    // other methods

    /**
     * Generate identity matrix.
     * @return 4 by 4 Identity matrix
     */
    public static Matrix4f id() {
        Matrix4f output = new Matrix4f();

        output.setContent(new float[] {
            1, 0, 0, 0, 
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1});
        return output;
    }


    /**
     * Generate an empty matrix of zeros.
     * @return zero matrix
     */
    public static Matrix4f zero() {
        Matrix4f output = new Matrix4f();
        output.setContent(new float[] {
            0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f
        });
        return output;
    }

    /**
     * Generate translation matrix.
     * @param translate translation vector
     * @return translation matrix
     */
    public static Matrix4f translate(Vector3f translate) {
        Matrix4f output = Matrix4f.id();
        output.set(3, 0, translate.getX());
        output.set(3, 1, translate.getY());
        output.set(3, 2, translate.getZ());
        return output;
    }
    
    /**
     * Generate rotation matrix.
     * @param angle rotation angle
     * @param axis rotation axis
     * @return rotation matrix
     */
    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f output = Matrix4f.id();

        float costheta = (float) Math.cos(Math.toRadians(angle));
        float sintheta = (float) Math.sin(Math.toRadians(angle));
        float umcostheta = 1 - costheta;
        output.set(0, 0, costheta + axis.getX() * axis.getX() * umcostheta);
        output.set(0, 1, axis.getX() * axis.getY() * umcostheta - axis.getZ() * sintheta);
        output.set(0, 2, axis.getX() * axis.getZ() * umcostheta + axis.getY() * sintheta);
        output.set(1, 0, axis.getY() * axis.getX() * umcostheta + axis.getZ() * sintheta);
        output.set(1, 1, costheta + axis.getY() * axis.getY() * umcostheta);
        output.set(1, 2, axis.getY() * axis.getZ() * umcostheta - axis.getX() * sintheta);
        output.set(2, 0, axis.getZ() * axis.getX() * umcostheta - axis.getY() * sintheta);
        output.set(2, 1, axis.getZ() * axis.getY() * umcostheta + axis.getX() * sintheta);
        output.set(2, 2, costheta + axis.getZ() * axis.getZ() * umcostheta);
        return output;
    }
    
    /**
     * Generate a scaling matrix.
     * @param factor scaling factor
     * @return scaling matrix
     */
    public static Matrix4f scale(Vector3f factor) {
        Matrix4f output = Matrix4f.id();

        output.set(0, 0, factor.getX());
        output.set(1, 1, factor.getY());
        output.set(2, 2, factor.getZ());

        return output;
    }
     /**
     * Generate a scaling matrix.
     * @param position position vector
     * @param rotation rotation vector
     * @param scale scaling factor
     * @return scaling matrix
     */
    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f output;

        Matrix4f tMatrix = Matrix4f.translate(position);
        Matrix4f rXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
        Matrix4f sMatrix = Matrix4f.scale(scale);

        Matrix4f rMatrix = Matrix4f.product(rXMatrix, Matrix4f.product(rYMatrix, rZMatrix));

        output = Matrix4f.product(tMatrix, Matrix4f.product(rMatrix, sMatrix));
        
        return output;
    }

    /**
     * Generate a projection matrix for the specified parameters.
     * @param aspect aspect ratio
     * @param fov field of view
     * @param nearDist near distance
     * @param farDist far distance
     * @return projection matrix
     */
    public static Matrix4f project(float aspect, float fov, float nearDist, float farDist) {
        Matrix4f output = Matrix4f.zero();
        
        float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
        float range = farDist - nearDist;

        output.set(0, 0, 1.0f / (aspect * tanFOV));
        output.set(1, 1, 1.0f / tanFOV);
        output.set(2, 2, -(farDist + nearDist) / range);
        output.set(3, 2, - 2 * farDist * nearDist / (farDist - nearDist));
        output.set(2, 3, -1.0f);

        return output;
    }

    /**
     * Generate a view matrix from the provided position and rotation.
     * @param position position vector (x, y, z)
     * @param rotation rotation vector (h, p, r)
     * @return view matrix
     */
    public static Matrix4f view(Vector3f position, Vector3f rotation) {
        Matrix4f output;

        Vector3f invertedPos = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
        // translation matrix
        Matrix4f tMatrix = Matrix4f.translate(invertedPos);
        Matrix4f rXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
        // rotation matrix
        Matrix4f rMatrix = Matrix4f.product(rZMatrix, Matrix4f.product(rYMatrix, rXMatrix));

        output = Matrix4f.product(tMatrix, rMatrix);
        
        return output;
    }

    /**
     * Calculate basic matrix product.
     * @param mat1 first matrix
     * @param mat2 second matrix
     * @return mat1*mat2
     */
    public static Matrix4f product(Matrix4f mat1, Matrix4f mat2) {
        Matrix4f output = Matrix4f.id();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                float sum = 0;
                for (int k = 0; k < SIZE; k++) {
                    sum += mat1.get(i, k) * mat2.get(k, j);
                }
                output.set(i, j, sum);
            }
        }
        return output;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(elements);
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
        Matrix4f other = (Matrix4f) obj;
        if (!Arrays.equals(elements, other.elements))
            return false;
        return true;
    }
    
}
