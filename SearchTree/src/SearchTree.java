import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SearchTree {

    int value,height;
    SearchTree left,right;

    public SearchTree(int value){
        this.value = value;
        left = null;
        right = null;
    }

    public SearchTree(int[] array){
        ArraytoTree(array);
    }       // Array to Tree

    public void ArraytoTree(int []array){
        if (array.length > 0){
            this.value = array[0];
            for (int i = 1;i < array.length;i++){
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

    public SearchTree getRightChild(){ return right; }

    public SearchTree getLeftChild(){
        return left;
    }

    public static int getHeight(SearchTree root){
        if(root == null){
            return 0;
        }
        return (Math.max(getHeight(root.getLeftChild()), getHeight(root.getRightChild())) + 1);
    }

    public void add(int value){     // Aufruf der Methode addrecursive.
        addrecursive(this,value);
    }

    public SearchTree addrecursive(SearchTree root,int value){
        if (root == null){
            return new SearchTree(value);
        }
        if (value < root.value){
            root.left = addrecursive(root.left,value);
        }
        else if (value > root.value){
            root.right = addrecursive(root.right,value);
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
}
