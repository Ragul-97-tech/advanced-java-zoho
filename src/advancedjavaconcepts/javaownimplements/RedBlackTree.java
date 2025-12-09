package advancedjavaconcepts.javaownimplements;

import java.util.Objects;

import static advancedjavaconcepts.javaownimplements.Color.RED;
import static advancedjavaconcepts.javaownimplements.Color.BLACK;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int size;

    @Override
    public Tree<T> insert(T data) {
        size++;
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        return this;
    }

    public Node<T> insert(Node<T> node, Node<T> nodeToInsert) {
        if (Objects.isNull(node)) return nodeToInsert;

        if (nodeToInsert.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), nodeToInsert));
            node.getLeftChild().setParent(node);
        } else if (nodeToInsert.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(node.getRightChild(), nodeToInsert));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    private void satisfyingRules(Node<T> node) {
        Node<T> parent = node.getParent();
        if (node != root && parent.getColor() == RED) {
            Node<T> grandParent = node.getParent().getParent();
            Node<T> uncle = parent.isLeftChild() ? grandParent.getRightChild() : grandParent.getLeftChild();

            if (uncle != null && uncle.getColor() == RED)
                recoloring(parent, uncle, grandParent);
            else if (parent.isLeftChild())
                leftBranch(node, parent, grandParent);
            else if (!parent.isLeftChild())
                rightBranch(node, parent, grandParent);
        }
        root.setColor(BLACK);
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
        node.setLeftChild(leftNode.getRightChild());
        if (node.getLeftChild() != null) {
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
        if (node.getRightChild() != null) {
            node.getRightChild().setParent(node);
        }
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, rightNode);
        node.setParent(rightNode);
    }

    private void updateChildrenOfParentNode(Node<T> node, Node<T> tempNode) {
        if (node.getParent() == null)
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
        if (node != null) {
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

    public T getMin(Node<T> node) {
        if (node.getLeftChild() != null)
            return getMin(node.getLeftChild());
        return node.getData();
    }

    @Override
    public T getMax() {
        if (isEmpty()) return null;
        return getMax(root);
    }

    public T getMax(Node<T> node) {
        if (node.getRightChild() != null)
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
