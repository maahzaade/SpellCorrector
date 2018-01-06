package algo;

import java.util.*;

/**
 * Created by Mahdiye on 10/1/2017.
 */
public class BKTree {

    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    Node root;

    public BKTree(String newNode) {
        if (root == null) {
            root = new Node(newNode);
        }
    }


    public int add(Node presentNode, String inputNode) {
        int editDistance = levenshteinDistance.measureLevenDist(inputNode, presentNode.getParent());
//        System.out.println("trying to insert e=" + inputNode + " at root " + presentNode.getParent().toString());
        if (!presentNode.getChildren().containsKey(editDistance)) {
            addToNodeChildren(presentNode, inputNode, editDistance);
            return 0;
        } else {
//            System.out.println("collision at distance" + editDistance);
            Node currentNode = presentNode.getChildren().get(editDistance);
            add(currentNode, inputNode);
        }

        return 1;
    }

    public void addToNodeChildren(Node currentNode, String inputNode, int distance) {
        Node newNode = new Node(inputNode);
        currentNode.getChildren().put(distance, newNode);
    }


    public ArrayList<String> listMisspelledNodes(String inputNode, int maxDist, Node rootNode, ArrayList<String> expectedCorrectWordList) {

        int editDistance = levenshteinDistance.measureLevenDist(inputNode, rootNode.getParent());
        if (editDistance <= maxDist) {
            expectedCorrectWordList.add(rootNode.getParent());
        }

        if (editDistance == 0) {
            return new ArrayList<String>();
        }

        int lowerBound = editDistance - maxDist;
        int upperBound = editDistance + maxDist;

        Set set = rootNode.getChildren().entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry element = (Map.Entry) iterator.next();
            if (((int) element.getKey() <= upperBound) && ((int) element.getKey() >= lowerBound)) {
                Node parentNode = (Node) element.getValue();
                listMisspelledNodes(inputNode, maxDist, parentNode, expectedCorrectWordList);
            }
        }

        return expectedCorrectWordList;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
