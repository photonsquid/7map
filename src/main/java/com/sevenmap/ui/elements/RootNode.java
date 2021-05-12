package com.sevenmap.ui.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sevenmap.exceptions.IncorrectChildTypeError;

/** */
public abstract class RootNode {
    
    protected List<Node> hiddenChildren;
    protected List<Node> shownChildren;

    private static Integer lastId = 0;
    private String id;
    protected String name;

    protected RootNode() {
        id = String.format("N%s", lastId++);
        this.hiddenChildren = new ArrayList<>();
        this.shownChildren = new ArrayList<>();
        name = "RootNode";
    }

    /**
     * Add a child to a Node.
     * @param child the child to be added
     */
    protected void addChild(Node child) {
        this.shownChildren.add(child);
    }

    /**
     * Remove a child from a Node's children list
     * @param child
     */
    protected void delChild(RootNode child) {
        if (!this.hiddenChildren.remove(child)) {
            this.shownChildren.remove(child);
        }
    }

    /**
     * Get the Node's children.
     * @return children ArrayList
     * @see {@link #getHiddenChildren()}
     * @see {@link #getShownChildren()}
     */
    public List<Node> getChildren() {
        return Stream.concat(hiddenChildren.stream(), shownChildren.stream()).collect(Collectors.toList());
    }

    /**
     * Get the Node's visible children.
     * @return visible children list
     * @see {@link #getChildren()}
     * @see {@link #getHiddenChildren()}
     */
    public List<Node> getShownChildren() {
        return shownChildren;
    }

    /**
     * Get the Node's hidden children.
     * @return hidden children list
     * @see {@link #getShownChildren()}
     * @see {@link #getChildren()}
     */
    public List<Node> getHiddenChildren() {
        return hiddenChildren;
    }

    /**
     * Determine if the node has children or if not.
     * @return true if the node does have children
     */
    public boolean hasChildren() {
        return (shownChildren.size() + hiddenChildren.size() )> 0;
    }

    /**
     * Get the Node's unique ID.
     * <p>
     * A Node's ID matches the following format : {@code ^N\d+$}
     * </p>
     * @return the node's ID
     */
    public String getID() {
        return id;
    }

    /**
     * Transfer a child from the 'hidden' category to the 'shown' one.
     * @param child the child which is meant to be revealed
     */
    public void showChild(Node child) {
        if (hiddenChildren.remove(child)) {
            shownChildren.add(child);
        }
    }

    /**
     * Transfer a child from the 'shown' category to the 'hidden' one.
     * @param child the child which is meant to be hidden
     */
    public void hideChild(Node child) {
        if (shownChildren.remove(child)) {
            hiddenChildren.add(child);
        }
    }

    /**
     * Check if the child can be affected to this object.
     * This method is meant to be overriden by any class 
     * inheriting from RootNode at some point.
     * <p>
     * The purpose of this class is to ensure the stability of the many
     * casts used in subclasses (such as GeomNode)
     * <p/>
     * @param child the child on which the compatibility check has to be executed
     */
    public void compatibilityCheck(Node child) {
        if (!(child instanceof Node)) {
            throw new IncorrectChildTypeError("Node element can only receive children of types Node or lower.");
        }
    }

    /**
     * Destoy all child elements and the node itself.
     */
    public void destroy() {
        this.hiddenChildren.forEach(Node::destroy);
        this.shownChildren.forEach(Node::destroy);
    }

    /**
     * Display a nice tree showing the node structure beneath 
     * the current node.
     */
    public void tree() {
        System.out.printf("%s %s%n", this.getID(), this.name);
        tree("");
    }

    public void tree(String shift) {
        List<Node> children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            System.out.printf("%s%s %s %s%n", shift, (i + 1 == children.size()) ? "└":"├", child.getID(), child.name);
            child.tree(String.format("%s%s", shift, (i < children.size() - 1) ? "│ ":"  "));
        }
    }
}
