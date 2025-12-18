package advancedjavaconcepts.javaownimplements;

import java.util.Objects;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T>, iterator<T> {
    private Node<T> root;
    private int size;

    @Override
    public boolean insert(T data) {
        if (searchElement(root, data) != null) {
            return false;
        }
        size++;
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        satisfyingInsertRules(node);
        return true;
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
//        while parent is red
        while (node != root && node.getParent().getColor() == Color.RED) {
            Node<T> parent = node.getParent();
            Node<T> grandParent = node.getParent().getParent();

            if (grandParent == null) break;

            if (parent.isLeftChild()) {
                Node<T> uncle = grandParent.getRightChild();
                // Case 1: uncle is RED -> recolor
                if (uncle != null && uncle.getColor() == Color.RED) {
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    node = grandParent;
                }
                else {
                    // Case 2: node is right child -> rotate left at parent (convert to left-left)
                    if (!node.isLeftChild()) {
                        node = parent;
                        rotateLeft(node);
                        parent = node.getParent();
                        grandParent = parent.getParent();
                    }
                    // Case 3: node is left-left -> recolor parent, grandparent and rotate right at grandparent
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    rotateLeft(grandParent);
                }
            }
            else {
                // parent is right child
                Node<T> uncle = grandParent.getLeftChild();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    node = grandParent;
                }
                else {
                    // If node is left child -> rotate right at parent (convert to right-right)
                    if (node.isLeftChild()) {
                        node = parent;
                        rotateRight(node);
                        parent = node.getParent();
                        grandParent = parent.getParent();
                    }
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    rotateLeft(grandParent);
                }
            }
        }
        if (root != null) root.setColor(Color.BLACK);
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        if (leftNode == null) return;

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
        if (rightNode == null) return;

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
        if (Objects.isNull(node.getParent())) {
            root = tempNode;
        }
        else if (node.isLeftChild()) {
            node.getParent().setLeftChild(tempNode);
        }
        else {
            node.getParent().setRightChild(tempNode);
        }
    }

    private void recoloring(Node<T> parent, Node<T> uncle, Node<T> grandParent) {
        if (parent != null) parent.flipColor();
        if (uncle != null) uncle.flipColor();
        if (grandParent != null) grandParent.flipColor();
    }


    public boolean remove(T node) {
        Node<T> nodeToDelete = searchElement(root, node);
        if (Objects.isNull(nodeToDelete)) return false;

        Node<T> replacementNode = nodeToDelete;
        Node<T> childOfReplacement;

        Color colorOfReplaceNode = replacementNode.getColor();
        // case 1: If left child node is null or none -> replace with right child
        if (Objects.isNull(nodeToDelete.getLeftChild())) {
            childOfReplacement = nodeToDelete.getRightChild();
            transplant(nodeToDelete, nodeToDelete.getRightChild());
        }
        // case 2: If no right child node -> replace with left child
        else if (Objects.isNull(nodeToDelete.getRightChild())) {
            childOfReplacement = nodeToDelete.getLeftChild();
            transplant(nodeToDelete, nodeToDelete.getLeftChild());
        }
        // case 3: Two children: find successor (min in right subtree)
        else {
            replacementNode = getMinNode(nodeToDelete.getRightChild());
            colorOfReplaceNode = replacementNode.getColor();
            childOfReplacement = replacementNode.getRightChild();
            if (replacementNode.getParent() == nodeToDelete) {
                if (!Objects.isNull(childOfReplacement)) {
                    childOfReplacement.setParent(replacementNode);
                }

            }
            else {
                transplant(replacementNode, replacementNode.getRightChild());
                replacementNode.setRightChild(nodeToDelete.getRightChild());
                if (replacementNode.getRightChild() != null) {
                    replacementNode.getRightChild().setParent(replacementNode);
                }

            }
            transplant(nodeToDelete, replacementNode);
            replacementNode.setLeftChild(nodeToDelete.getLeftChild());
            if (replacementNode.getLeftChild() != null) {
                replacementNode.getLeftChild().setParent(replacementNode);
            }
            replacementNode.setColor(nodeToDelete.getColor());
        }

        size--;
        if (colorOfReplaceNode == Color.BLACK) {
            Node<T> replaceNode = (!Objects.isNull(childOfReplacement) ? childOfReplacement.getParent() : null);
            satisfyingDeleteRules(childOfReplacement, replaceNode);
        }

        return true;
    }

    private boolean isBlack(Node<T> n) {
        return Objects.isNull(n) || n.getColor() == Color.BLACK;
    }

    public void satisfyingDeleteRules(Node<T> currentNode, Node<T> parentNode) {
        while (currentNode != root && isBlack(currentNode)) {
            if (parentNode == null) break;
            // Case: current node is a left child
            if (currentNode == parentNode.getLeftChild()) {
                Node<T> sibling = parentNode.getRightChild();

                // case 1: sibling is red
                if (sibling != null && sibling.getColor() ==  Color.RED) {
                    sibling.setColor(Color.BLACK);
                    parentNode.setColor(Color.RED);
                    rotateLeft(parentNode);
                    sibling = parentNode.getRightChild();
                }

                //case 2: sibling's child both black
                boolean isSiblingLeftIsBlack = isBlack(sibling == null ? null : sibling.getLeftChild());
                boolean isSiblingRightIsBlack = isBlack(sibling == null ? null : sibling.getRightChild());

                if (sibling != null && isSiblingLeftIsBlack && isSiblingRightIsBlack) {
                    sibling.setColor(Color.RED);
                    currentNode = parentNode;
                    parentNode = parentNode.getParent();
                }
                // case 3: siblings's right child is black --> rotate sibling
                else {
                    if (sibling != null && isSiblingRightIsBlack) {
                        if (!Objects.isNull(sibling.getLeftChild())) {
                            sibling.getLeftChild().setColor(Color.BLACK);
                        }
                        sibling.setColor(Color.RED);
                        rotateRight(sibling);
                        sibling = parentNode.getRightChild();
                    }

                    // case 4: sibling's right child is red
                    if (sibling != null) {
                        sibling.setColor(parentNode.getColor());
                        parentNode.setColor(Color.BLACK);
                        if (!Objects.isNull(sibling.getRightChild())) {
                            sibling.getRightChild().setColor(Color.BLACK);
                        }
                    }
                    rotateLeft(parentNode);
                    currentNode = root;
                }
            }
            else {
                // current node is right child
                Node<T> sibling = parentNode.getLeftChild();

                // case 1: siblings is red
                if (sibling != null && sibling.getColor() == Color.RED) {
                    sibling.setColor(Color.BLACK);
                    parentNode.setColor(Color.RED);
                    rotateRight(parentNode);
                    sibling = parentNode.getLeftChild();
                }

                boolean isSiblingLeftIsBlack = isBlack(sibling == null ? null : sibling.getLeftChild());
                boolean isSiblingRightIsBlack = isBlack(sibling == null ? null : sibling.getRightChild());

                if (sibling != null && isSiblingLeftIsBlack && isSiblingRightIsBlack) {
                    sibling.setColor(Color.RED);
                    currentNode = parentNode;
                    parentNode = parentNode.getParent();
                }
                else {
                    if (sibling != null && isSiblingLeftIsBlack) {
                        if (sibling.getRightChild() != null) {
                            sibling.getRightChild().setColor(Color.BLACK);
                        }
                        sibling.setColor(Color.RED);
                        rotateLeft(sibling);
                        sibling = parentNode.getLeftChild();
                    }

                    if (sibling != null) {
                        sibling.setColor(parentNode.getColor());
                        parentNode.setColor(Color.BLACK);
                        if (sibling.getLeftChild() != null) {
                            sibling.getLeftChild().setColor(Color.BLACK);
                        }
                    }
                    rotateRight(parentNode);
                    currentNode = root;
                }
            }
        }

        if (currentNode != null) {
            currentNode.setColor(Color.BLACK);
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
        if (Objects.isNull(n1.getParent())) {
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
            System.out.print(node + ", ");
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

    public boolean contains(T node) {
        if (searchElement(root, node) != null) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
