package advancedjavaconcepts.javaownimplements;

import java.util.Objects;

import static advancedjavaconcepts.javaownimplements.Colorful.RED;
import static advancedjavaconcepts.javaownimplements.Colorful.BLACK;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int size;

//        40, 20, 60, 35, 10, 30, 50, 70, 25, 65, 15
    @Override
    public Tree<T> insert(T data) {
        size++;
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        System.out.println("node: "+node);
        System.out.println("root: "+root);
        satisfyingRules(node);
        return this;
    }

    private Node<T> insert(Node<T> node, Node<T> nodeToInsert) {
        if (Objects.isNull(node)) return nodeToInsert;

        if (nodeToInsert.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), nodeToInsert));
            node.getLeftChild().setParent(node);
        }
        else if (nodeToInsert.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(node.getRightChild(), nodeToInsert));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    private void satisfyingRules(Node<T> node) {
        Node<T> parent = node.getParent();
        if (node != root && parent.getColor() == Color.RED) {
            Node<T> grandParent = node.getParent().getParent();
            Node<T> uncle = parent.isLeftChild() ? grandParent.getRightChild() : grandParent.getLeftChild();
            System.out.println(grandParent);
            System.out.println(uncle);

            if (!Objects.isNull(uncle) && uncle.getColor() == Color.RED)
                recoloring(parent, uncle, grandParent);
            else if (parent.isLeftChild())
                leftBranch(node, parent, grandParent);
            else if (!parent.isLeftChild())
                rightBranch(node, parent, grandParent);
        }
        root.setColor(Color.BLACK);
    }

    private void leftBranch(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (!node.isLeftChild())
            rotateRight(parent);
        parent.flipColor();
        grandParent.flipColor();
        rotateRight(grandParent);
        satisfyingRules(node.isLeftChild() ? grandParent : parent);
    }

    private void rightBranch(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (node.isLeftChild())
            rotateRight(parent);
        parent.flipColor();
        grandParent.flipColor();
        rotateLeft(grandParent);
        satisfyingRules(node.isLeftChild() ? parent : grandParent);
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        System.out.println(leftNode);
        node.setLeftChild(leftNode.getRightChild());
        if (!Objects.isNull(node.getLeftChild())) {
            node.getLeftChild().setParent(node);
        }
        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, leftNode);
        node.setParent(leftNode);
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if (!Objects.isNull(node.getRightChild())) {
            node.getRightChild().setParent(node);
        }
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, rightNode);
        node.setParent(rightNode);
    }

    private void updateChildrenOfParentNode(Node<T> node, Node<T> tempNode) {
        if (Objects.isNull(node.getParent()))
            root = tempNode;
        else if (node.isLeftChild())
            node.getParent().setLeftChild(tempNode);
        else
            node.getParent().setRightChild(tempNode);
    }

    private void recoloring(Node<T> parent, Node<T> uncle, Node<T> grandParent) {
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        satisfyingRules(grandParent);
    }

    public void traverse() {
        traverseInorder(root);
    }

    private void traverseInorder(Node<T> node) {
        if (!Objects.isNull(node)) {
            traverseInorder(node.getLeftChild());
            System.out.println(node);
            traverseInorder(node.getRightChild());
        }
    }

    @Override
    public T getMin() {
        if (isEmpty()) return null;
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (!Objects.isNull(node.getLeftChild()))
            return getMin(node.getLeftChild());
        return node.getData();
    }

    @Override
    public T getMax() {
        if (isEmpty()) return null;
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (!Objects.isNull(node.getRightChild()))
            return getMax(node.getRightChild());
        return node.getData();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
