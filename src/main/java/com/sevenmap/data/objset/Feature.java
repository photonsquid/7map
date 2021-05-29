package com.sevenmap.data.objset;

@Deprecated
public class Feature {
    private boolean displayName;
    private String name;
    private Point[] p;
    private Types type;
    private Category cat;
    private String[] attribut;

    public Feature(boolean displayName, String name, Point[] p, Types type, Category cat, String[] attribut) {
        this.displayName = displayName;
        this.name = name;
        this.p = p;
        this.type = type;
        this.cat = cat;
        this.attribut = attribut;
    }

    public Point[] getPoints() {
        return this.p;
    }
}
