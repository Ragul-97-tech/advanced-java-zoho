package advancedjavaconcepts.advancedjavalearnings;

import java.util.Stack;

class BinaryNode {
    int data;
    BinaryNode left, right;
    BinaryNode(int data) {
       this.data = data;
    }

    @Override
    public String toString() {
        return data+"";
    }
}

public class BinaryTreeStructure {
    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(10);
        root.left = new BinaryNode(20);
        root.right = new BinaryNode(30);
        root.left.right = new BinaryNode(50);
        root.left.left = new BinaryNode(40);

        inOrderTraverse(root);
        System.out.println();
        preOrderTraverse(root);
        System.out.println();
        postOrderTraverse(root);
        System.out.println();

        inOrderStack(root);
    }

    public static void inOrderStack(BinaryNode root) {
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.data + ", ");
            current = current.right;
        }
    }

    public static void inOrderTraverse(BinaryNode root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.data + ", ");
            inOrderTraverse(root.right);
        }
        return;
    }

    public static void preOrderTraverse(BinaryNode root) {
        if (root != null) {
            System.out.print(root.data +", ");
            inOrderTraverse(root.left);
            inOrderTraverse(root.right);
        }
        return;
    }

    public static void postOrderTraverse(BinaryNode root) {
        if (root != null) {
            System.out.print(root.data +", ");
            inOrderTraverse(root.left);
            inOrderTraverse(root.right);
        }
        return;
    }
}
