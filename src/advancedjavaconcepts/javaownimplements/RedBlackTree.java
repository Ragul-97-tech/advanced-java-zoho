package advancedjavaconcepts.javaownimplements;

import java.util.Objects;

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
        satisfyingInsertRules(node);
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

    private void satisfyingInsertRules(Node<T> node) {
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
        satisfyingInsertRules(node.isLeftChild() ? grandParent : parent);
    }

    private void rightBranch(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (node.isLeftChild())
            rotateRight(parent);
        parent.flipColor();
        grandParent.flipColor();
        rotateLeft(grandParent);
        satisfyingInsertRules(node.isLeftChild() ? parent : grandParent);
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
        satisfyingInsertRules(grandParent);
    }
    
    @Override
    public boolean remove(T node) {
        Node<T> nodeToDelete = searchElement(root, node);
        if (Objects.isNull(nodeToDelete)) return false;

        Node<T> replacementNode = nodeToDelete;
        Node<T> childOfReplacement;

        Color colorOfReplaceNode = replacementNode.getColor();
        // case 1: If no left child node
        if (nodeToDelete.getLeftChild() == null) {
            childOfReplacement = nodeToDelete.getRightChild();
            transplant(nodeToDelete, nodeToDelete.getRightChild());
        }
        // case 2: If no right child node
        else if (nodeToDelete.getRightChild() == null) {
            childOfReplacement = nodeToDelete.getLeftChild();
            transplant(nodeToDelete, nodeToDelete.getLeftChild());
        }
        // case 3: Two children
        else {
            replacementNode = getMinNode(nodeToDelete.getRightChild());
            colorOfReplaceNode = replacementNode.getColor();
            childOfReplacement = replacementNode.getRightChild();
            if (replacementNode.getParent() == nodeToDelete) {
                if (!Objects.isNull(childOfReplacement)) {
                    childOfReplacement.setParent(replacementNode);
                }
                else {
                    transplant(replacementNode, replacementNode.getRightChild());
                    replacementNode.setRightChild(nodeToDelete.getRightChild());
                    replacementNode.getRightChild().setParent(replacementNode);
                }
                transplant(nodeToDelete, replacementNode);
                replacementNode.setLeftChild(nodeToDelete.getLeftChild());
                replacementNode.getLeftChild().setParent(replacementNode);
            }
            size--;
            if (colorOfReplaceNode == Color.BLACK) {
                Node<T> replaceNode = (!Objects.isNull(childOfReplacement) ? childOfReplacement.getParent() : null);
                satisfyingDeleteRules(childOfReplacement, replaceNode);
            }
        }
        return false;
    }

    public void satisfyingDeleteRules(Node<T> currentNode, Node<T> parentNode) {
        while (currentNode != root && (currentNode == null || currentNode.getColor() == Color.BLACK)) {

            // Case: current node is a left child
            if (currentNode == (parentNode != null ? parentNode.getLeftChild() : null)) {
                Node<T> sibling = parentNode.getRightChild();

                // case 1: sibling is red
                if (sibling.getColor() ==  Color.RED) {
                    sibling.setColor(Color.BLACK);
                    parentNode.setColor(Color.RED);
                    rotateLeft(parentNode);
                    sibling = parentNode.getRightChild();
                }

                //case 2: sibling's child both black
                boolean isSiblingLeftIsBlack = (sibling.getLeftChild() == null || sibling.getLeftChild().getColor() == Color.BLACK);
                boolean isSiblingRightIsBlack = (sibling.getRightChild() == null || sibling.getRightChild().getColor() == Color.BLACK);

                if (isSiblingLeftIsBlack && isSiblingRightIsBlack) {
                    sibling.setColor(Color.RED);
                    currentNode = parentNode;
                    parentNode = parentNode.getParent();
                }

            }
        }
    }

    public Node<T> searchElement(Node<T> node, T nodeToFind) {
        if (Objects.isNull(node)) return null;

        int compares = nodeToFind.compareTo(node.getData());
        if (compares == 0) return node;
        if (compares < 0) return searchElement(node.getLeftChild(), nodeToFind);
        return searchElement(node.getRightChild(), nodeToFind);
    }

    public void transplant(Node<T> n1, Node<T> n2) {
        if (n1.getParent() == null) {
            root = n2;
        } else if (n1.isLeftChild()) {
            n1.getParent().setLeftChild(n2);
        } else {
            n1.getParent().setRightChild(n2);
        }
        if (!Objects.isNull(n2))
            n2.setParent(n1.getParent());
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

    private Node<T> getMinNode(Node<T> node) {
        if (!Objects.isNull(node.getLeftChild()))
            return getMinNode(node.getLeftChild());
        return node;
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
