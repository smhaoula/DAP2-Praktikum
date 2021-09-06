import sun.util.locale.provider.AvailableLanguageTags;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AVLTree {

    int value,height;
    AVLTree left,right;

    public AVLTree(int value){
        this.value = value;
        left = null;
        right = null;
    }

    public AVLTree(int[] array){
        if (array.length > 0){
            System.out.println("Fuege " + array[0] + " in AVL-Baum ein.");
            this.value = array[0];
            for (int i = 1;i < array.length;i++){
                System.out.println("Fuege " + array[i] + " in AVL-Baum ein.");
                add(array[i]);
            }
        }
        else {
            throw new NoSuchElementException();

        }
    }

    public int getValue() {
        return value;
    }

    public AVLTree getRightChild(){ return right; }

    public AVLTree getLeftChild(){
        return left;
    }

    public static int getHeight(AVLTree root){
        if(root == null){
            return 0;
        }
        return (Math.max(getHeight(root.getLeftChild()), getHeight(root.getRightChild())) + 1);
    }

    public static int getBalance(AVLTree root) {
        if (root == null)
            return 0;
        return getHeight(root.left) - getHeight(root.right);
    }

    public void add(int value) {
        addrecursive(this, value);
    }

    private void rotateLeft(AVLTree root){
        if (root.right != null) {
            AVLTree speicher = root.right;
            if (speicher.left != null) {
                AVLTree speicher2 = speicher.left;
                speicher.left = root;
                root.right = speicher2;
            }
            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
            speicher.height = Math.max(getHeight(speicher.left), getHeight(speicher.right)) + 1;
        }

    }

    private void rotateRight(AVLTree root){
        if (root.left != null) {
            AVLTree speicher = root.left;
            if (speicher.right != null) {
                AVLTree speicher2 = speicher.right;
                speicher.right = root;
                root.left = speicher2;
            }
            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
            speicher.height = Math.max(getHeight(speicher.left), getHeight(speicher.right)) + 1;
        }
    }

    public AVLTree addrecursive(AVLTree root,int value){

        if (root == null){
            return (new AVLTree(value));
        }
        if (value < root.value){
            root.left = addrecursive(root.left,value);
        }
        else if (value > root.value){
            root.right = addrecursive(root.right,value);
        }
        else {
            return root;
        }

        int linkerbaum = getHeight(root.left);
        int rechterbaum = getHeight(root.right);

        // Aktualisierung von Height des Knoten.
        root.height = Math.max(linkerbaum,rechterbaum) + 1;

        // Balancefaktor rechnen.
        int balance = getBalance(root);

        if (balance > 1 && value < root.left.getValue()){// Links links Fall
            System.out.println("Linker Teilbaum von " + "''" + root.getValue() + "''" + " hat Hoehe " + linkerbaum + " Rechter Teilbaum hat Hoehe " + rechterbaum);
            System.out.println("Fuehre Rechts-Rotation durch");
            rotateRight(root);
        }
        if (balance < -1 && value > root.right.getValue()){   // Rechts rechts Fall
            System.out.println(" Rechter Teilbaum " + "''" + root.getValue() + "''" + " hat Hoehe " + rechterbaum + " Linker Teilbaum von " + linkerbaum);
            System.out.println("Fuehre Links-Rotation durch");
            rotateLeft(root);
        }
        if (balance > 1 && value > root.left.getValue()){    // Links rechts Fall
            System.out.println(" Rechter Teilbaum " + "''" + root.getValue() + "''" + " hat Hoehe " + rechterbaum + " Linker Teilbaum von " + linkerbaum);
            System.out.println("Fuehre Rechts-Links-Rotation durch");
            rotateLeft(root.left);
            rotateRight(root);
        }
        if (balance < -1 && value < root.right.getValue()){    // Rechts links Fall
            System.out.println("Linker Teilbaum von " + "''" + root.getValue() + "''" + " hat Hoehe " + linkerbaum + " Rechter Teilbaum hat Hoehe " + rechterbaum);
            System.out.println("Fuehre Links-Rechts-Rotation durch");
            rotateRight(root.right);
            rotateLeft(root);
        }

        return root;
    }

    public void inOrder() {
        if (this != null){
            if (left != null)
                left.inOrder();
            System.out.print(this.getValue() + " ");
            if (right != null)
                right.inOrder();
        }
    }

    public void preOrder() {
        if (this != null) {
            System.out.print(this.getValue() + " ");
            if (left != null)
                left.preOrder();
            if (right != null)
                right.preOrder();
        }
    }

    public void postOrder(){
        if (this != null) {
            if (left != null)
                left.postOrder();
            if (right != null)
                right.postOrder();
            System.out.print(this.getValue() + " ");
        }
    }

    public static int[] ListtoArray(ArrayList list) {   // Hilfsmethode.
        assert !(list.isEmpty()): "List ist leer";
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) list.get(i);
        }
        return arr;
    }

    /*




        if (balance > 1 && value < root.left.getValue()){// Links links Fall
            System.out.println("Linker Teilbaum von " + "''" + root.getValue() + "''" + " hat Hoehe " + linkerbaum + " Rechter Teilbaum hat Hoehe " + rechterbaum);
            System.out.println("Fuehre Rechts-Rotation durch");
            rotateRight(root);
        }
        if (balance < -1 && value > root.right.getValue()){   // Rechts rechts Fall
            System.out.println(" Rechter Teilbaum " + "''" + root.getValue() + "''" + " hat Hoehe " + rechterbaum + " Linker Teilbaum von " + linkerbaum);
            System.out.println("Fuehre Links-Rotation durch");
            rotateLeft(root);
        }
        if (balance > 1 && value > root.left.getValue()){    // Links rechts Fall
            System.out.println(" Rechter Teilbaum " + "''" + root.getValue() + "''" + " hat Hoehe " + rechterbaum + " Linker Teilbaum von " + linkerbaum);
            System.out.println("Fuehre Rechts-Links-Rotation durch");
            rotateLeft(root.left);
            rotateRight(root);
        }
        if (balance < -1 && value < root.right.getValue()){    // Rechts links Fall
            System.out.println("Linker Teilbaum von " + "''" + root.getValue() + "''" + " hat Hoehe " + linkerbaum + " Rechter Teilbaum hat Hoehe " + rechterbaum);
            System.out.println("Fuehre Links-Rechts-Rotation durch");
            rotateRight(root.right);
            rotateLeft(root);
        }
    */
}
