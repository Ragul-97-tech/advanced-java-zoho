package advancedjavaconcepts.advancedjavalearnings;

class BinarySearchNode {
    int data;
    BinarySearchNode left, right, root;
    BinarySearchNode(int data) {
        this.data = data;
    }
}

public class BinarySearchTree {
    BinarySearchNode root;
    boolean insert(int data) {
        if (root == null)
            root = new BinarySearchNode(data);
        return true;
    }

    BinarySearchNode insert(BinarySearchNode node, int data) {
        return null;
    }
}
