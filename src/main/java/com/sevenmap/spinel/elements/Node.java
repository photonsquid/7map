package com.sevenmap.spinel.elements;

import com.sevenmap.exceptions.IncorrectChildTypeError;

public class Node extends RootNode {
    protected RootNode parent;

    public Node() {
        name = this.getClass().getSimpleName();
    }

    public Node(String name) {
        this.name = name;
    }

    /**
     * Reparent a Node to another Node element.
     * 
     * @param parent the parent Node
     */
    public void setParent(RootNode parent) {
        parent.compatibilityCheck(this);
        // remove this object from the old parent's children list
        if (this.parent != null) {
            this.parent.delChild(this);
        }
        // add this object to the new parent's children list
        this.parent = parent;
        this.parent.addChild(this);
    }

    /**
     * Get a Node's parent Node.
     * 
     * @return the parent Node
     */
    public RootNode getParent() {
        return parent;
    }

    /**
     * Mark the node as shown.
     * <p>
     * The node will no longer be rendered after this call.
     * <p/>
     */
    public void show() {
        parent.showChild(this);
    }

    /**
     * Mark the node as hidden.
     * <p>
     * The node will no longer be rendered after this call.
     * <p/>
     */
    public void hide() {
        parent.hideChild(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compatibilityCheck(Node child) {
        if (!(child instanceof Node)) {
            throw new IncorrectChildTypeError("Node element can only receive children of types Node or lower.");
        }
    }
}