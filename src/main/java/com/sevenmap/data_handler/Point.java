package com.sevenmap.data_handler;

public class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void add(Point p) {
        this.x += p.getX();
        this.y += p.getY();
    }

    public void divide(float divider) {
        this.x /= divider;
        this.y /= divider;
    }
}