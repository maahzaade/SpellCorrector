package algo;

import java.util.HashMap;

/**
 * Created by Mahdiye on 10/1/2017.
 */
public class Node {

    String parent;
    HashMap<Integer, Node> children = new HashMap<>();

    Node(String parent)
    {
        this.parent = parent;
    }
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public HashMap<Integer, Node> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Integer, Node> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return parent;
    }
}
