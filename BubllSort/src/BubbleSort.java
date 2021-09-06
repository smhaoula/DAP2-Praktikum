import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0;i < n; i++){
            for (int j = n - 1; j >= i + 1 ;j--){
                if (array[j - 1] < array[j]){
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }

    public static int [] beffuellung(int size){
        int []arr = new int [size];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = i;
        }
        return arr;
    }

    public static void main(String[] args) {
        int size = 50000;           // Laenge des Feldes.
        int []arr;                  // Erstellung eines Feldes
        long tStart, tEnd;
        double secs;
        arr = BubbleSort.beffuellung(size);         // Beffuellung des Feldes.
        System.out.println(Arrays.toString(arr));   // Ausgabe des Feldes.
        tStart = System.currentTimeMillis();        // Beginn der Messung.
        BubbleSort.bubbleSort(arr);                 // Sortierung des Feldes mithilfe der Bubblesort.
        tEnd = System.currentTimeMillis();          // Ende der Messung.
        secs = (tEnd - tStart) / 1000.0;
        System.out.println(Arrays.toString(arr));
        System.out.println(secs + "s");             // Ausgabe der berechneten Laufzeit.
    }
}
