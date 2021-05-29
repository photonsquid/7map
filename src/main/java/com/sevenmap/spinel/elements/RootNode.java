package com.sevenmap.spinel.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sevenmap.exceptions.IncorrectChildTypeException;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.geom.GeomNode;
import com.sevenmap.spinel.elements.gui.GuiNode;

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
     * 
     * @param child the child to be added
     */
    protected void addChild(Node child) {
        Runnable action = () -> {
            this.shownChildren.add(child);
        };
        if (Engine.getInstance().isRunning()) {
            Engine.getInstance().getWindow().stack(action);
        } else {
            action.run();
        }

    }

    /**
     * Remove a child from a Node's shownChildren list
     * 
     * @param child
     */
    protected void delChild(RootNode child) {
        Runnable action = () -> {
            if (!this.hiddenChildren.remove(child)) {
                this.shownChildren.remove(child);
            }
        };
        if (Engine.getInstance().isRunning()) {
            Engine.getInstance().getWindow().stack(action);
        } else {
            action.run();
        }
    }

    /**
     * Get the Node's shownChildren.
     * 
     * @return shownChildren ArrayList
     * @see {@link #getHiddenChildren()}
     * @see {@link #getShownChildren()}
     */
    public List<Node> getChildren() {
        return Stream.concat(hiddenChildren.stream(), shownChildren.stream()).collect(Collectors.toList());
    }

    /**
     * Get the Node's visible shownChildren.
     * 
     * @return visible shownChildren list
     * @see {@link #getChildren()}
     * @see {@link #getHiddenChildren()}
     */
    public List<Node> getShownChildren() {
        return shownChildren;
    }

    /**
     * Get the Node's hidden shownChildren.
     * 
     * @return hidden shownChildren list
     * @see {@link #getShownChildren()}
     * @see {@link #getChildren()}
     */
    public List<Node> getHiddenChildren() {
        return hiddenChildren;
    }

    /**
     * Determine if the node has shownChildren or if not.
     * 
     * @return true if the node does have shownChildren
     */
    public boolean hasChildren() {
        return (shownChildren.size() + hiddenChildren.size()) > 0;
    }

    /**
     * Get the Node's unique ID.
     * <p>
     * A Node's ID matches the following format : {@code ^N\d+$}
     * </p>
     * 
     * @return the node's ID
     */
    public String getID() {
        return id;
    }

    /**
     * Transfer a child from the 'hidden' category to the 'shown' one.
     * 
     * @param child the child which is meant to be revealed
     */
    public void showChild(Node child) {
        Runnable action = () -> {
            if (hiddenChildren.remove(child)) {
                shownChildren.add(child);
            }
        };
        if (Engine.getInstance().isRunning()) {
            Engine.getInstance().getWindow().stack(action);
        } else {
            action.run();
        }
    }

    /**
     * Transfer a child from the 'shown' category to the 'hidden' one.
     * 
     * @param child the child which is meant to be hidden
     */
    public void hideChild(Node child) {
        Runnable action = () -> {
            if (shownChildren.remove(child)) {
                hiddenChildren.add(child);
            }
        };
        if (Engine.getInstance().isRunning()) {
            Engine.getInstance().getWindow().stack(action);
        } else {
            action.run();
        }
    }

    /**
     * Check if the child can be affected to this object. This method is meant to be
     * overriden by any class inheriting from RootNode at some point.
     * <p>
     * The purpose of this class is to ensure the stability of the many casts used
     * in subclasses (such as GeomNode)
     * <p/>
     * 
     * @param child the child on which the compatibility check has to be executed
     */
    public void compatibilityCheck(Node child) {
        if (!(child instanceof Node)) {
            throw new IncorrectChildTypeException(
                    "Node element can only receive shownChildren of types Node or lower.");
        }
    }

    /**
     * Destoy all child elements and the node itself.
     * 
     * @implNote untested at runtime, might cause a threading exception
     */
    public void destroy() {
        this.hiddenChildren.forEach(Node::destroy);
        this.shownChildren.forEach(Node::destroy);
    }

    /**
     * Display a nice tree showing the node structure beneath the current node.
     */
    public void tree() {
        System.out.printf("%s %s%n", this.getID(), this.name);
        tree("");
    }

    public void tree(String shift) {
        List<Node> sChildren = getShownChildren();
        for (int i = 0; i < sChildren.size(); i++) {
            Node child = sChildren.get(i);
            // "shown" if the child has a mesh, "empty" if not
            String status = (child instanceof GeomNode) && ((GeomNode) child).hasMesh() || (child instanceof GuiNode)
                    ? "shown"
                    : "empty";
            System.out.printf("%s%s %s %s (%s)%n", shift, (i + 1 == sChildren.size()) ? "└" : "├", child.getID(),
                    child.name, status);
            child.tree(String.format("%s%s", shift, (i < sChildren.size() - 1) ? "│ " : "  "));
        }
        List<Node> hChildren = getHiddenChildren();
        for (int i = 0; i < hChildren.size(); i++) {
            Node child = hChildren.get(i);
            System.out.printf("%s%s %s %s (%s)%n", shift, (i + 1 == hChildren.size()) ? "└" : "├", child.getID(),
                    child.name, "hidden");
            child.tree(String.format("%s%s", shift, (i < hChildren.size() - 1) ? "│ " : "  "));
        }
    }
}
