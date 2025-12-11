package advancedjavaconcepts.javaownimplements;

import static advancedjavaconcepts.javaownimplements.Colorful.RED;
import static advancedjavaconcepts.javaownimplements.Colorful.BLACK;
import static advancedjavaconcepts.javaownimplements.Colorful.RESET;

public class Node<T extends Comparable<T>> {
    private T data;
    private Color color = Color.RED;
    private String colorful = RED;

    Node(T data) {
        this.data = data;
    }

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
        this.colorful = this.color.name().equalsIgnoreCase("black") ? RED : BLACK;
        this.color = this.color == Color.RED ? Color.BLACK : Color.RED;
    }

    public T getData() {
        return data;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.colorful = color.name().equalsIgnoreCase("black") ? BLACK : RED;
    }

    @Override
    public String toString() {
        return colorful + data + RESET;
    }
}
