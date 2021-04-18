package com.sevenmap.ui.elements;

import java.util.ArrayList;
import java.util.List;

/** */
public abstract class RootNode {
    
    protected List<Node> children;

    private static Integer lastId = 0;
    private String id;

    protected RootNode() {
        id = String.format("N%s", lastId++);
        this.children = new ArrayList<>();
    }

    /**
     * Add a child to an Node.
     * @param child the child to be added
     */
    public void addChild(Node child) {
        this.children.add(child);
    }

    /**
     * Remove a child from an Node's children list
     * @param child
     */
    public void delChild(RootNode child) {
        this.children.remove(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    /**
     * Get the Node's unique ID.
     * <p>
     * An Node's ID matches the following format : {@code ^N\d+$}
     * @return
     */
    public String getID() {
        return id;
    }
}
