import java.io.*;
import java.util.*;

public class AVLTree {
    public class Node {
        private Node left, right, parent;
        private int height = 1;
        private int value;

        private Node(int val) {
            this.value = val;
        }
    }

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private Node insert(Node node, int value) {
        /* 1.  Perform the normal BST rotation */
        if (node == null) {
            return (new Node(value));
        }

        if (value < node.value)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        /* 2. Update height of this ancestor node */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
           this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            System.out.printf("%d ", root.value);
            preOrder(root.right);
        }
    }

    public static void postOrder(Node root){
        if (root != null) {
                postOrder(root.left);
                postOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

    public static void inOrder(Node root) {
        if (root != null){
                inOrder(root.left);
                System.out.print(root.value + " ");
                inOrder(root.right);
        }
    }
}