package advancedjavaconcepts.javaownimplements;

import static advancedjavaconcepts.javaownimplements.Color.RED;
import static advancedjavaconcepts.javaownimplements.Color.BLACK;

public class Node<T extends Comparable<T>> {
    private T data;

    Node(T data) {
        this.data = data;
    }

    private String color = RED;

    private Node<T> leftChild, rightChild, parent;

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public boolean isLeftChild() {
        return this == parent.getLeftChild();
    }

    public void flipColor() {
        this.color = this.color == RED ? BLACK : RED;
    }

    public T getData() {
        return data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Value: " + data;
    }
}
