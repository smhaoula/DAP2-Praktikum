import java.util.Arrays;

public class Quicksort {

    public static int med3(int []arr,int l,int r){
        return median(arr[l],arr[(l + r) / 2],arr[r]);
    }

    public static int ninther(int []arr,int l,int r){
        int m1 = l + (r - l) * 1 / 3;
        int m2 = l + (r - l) * 2 / 3;
        return median(med3(arr,l,m1),med3(arr,m1,m2),med3(arr,m2,r));
    }

    public static int median(int l,int m, int r){
        if((l <= m && m <= r) || (r <= m && m <= l)){       // m ist der Median von den drei Werten.
            return m;
        }
        else if ((l <= r && r <= m) || (m <= r && r <= l)){ // r ist der Median von den drei Werten.
            return r;
        }
            return l;                                           // sonst ist l
    }

    public static void Quicksort(int[] arr, int l, int r){      // Pseudocode.
        if(l < r)
        {
            int i = l;                                          // linke Grenze.
            int j = r;                                          // rechte Grenze.
            int pivot = ninther(arr,l,r);                       // Pivot-Element auswählen.
            int tmp;                                            // Hilfsvariable zum Zwischenspeichern.
            while (i <= j){                                     //
                while (arr[i] < pivot){                         // Werte kleiner als Pivot.
                    i = i + 1;
                }
                while (arr[j] > pivot){                         // Werte groesser als Pivot.
                    j = j - 1;
                }
                if (i <= j){
                    tmp = arr[i];                               // arr[i] zwischenspeichern.
                    arr[i] = arr[j];                            //arr[j] in arr[i] ueberschreiben.
                    arr[j] = tmp;                               // alter Wert von arr[i] in arr[j] schreiben.
                    i = i + 1;                                  // Inkrementierung von i.
                    j = j - 1;                                  // Dekrementierung von j.
                }
            }
            Quicksort(arr, l, j);             // rekursiver Aufruf
            Quicksort(arr, i, r);             // rekursiver Aufruf
        }
    }

    public static boolean isSorted(int[] arr){
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < arr[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            assert (args.length == 1) : "Ungueltige Argumente";
            if (args.length == 1) {                             // Ueberpruefen,ob nur ein Argument eingegeben wurde.
                int argument = Integer.parseInt(args[0]);
                if (argument < 0) {
                    throw new RuntimeException();
                }
                int[] arr = new int[argument];
                long tStart, tEnd, msecs;

                //////////////////////////////////////////////////////////////////////////////////////
                for (int i = 0; i < argument; i++) {
                    assert i >= argument : "mit zu vielen Werten befüllt!";         // Befuellung des Feldes mit zufaelligen Zahlen.
                    arr[i] = (int) (Math.random() * 100);
                }
                //////////////////////////////////////////////////////////////////////////////////////

                System.out.println(Arrays.toString(arr));
                tStart = System.currentTimeMillis();
                Quicksort.Quicksort(arr, 0, argument - 1);
                tEnd = System.currentTimeMillis();
                msecs = tEnd - tStart;

                System.out.println(Arrays.toString(arr));
                assert !Quicksort.isSorted(arr) : "Fehler! Array wurde nicht sortiert";
                if (Quicksort.isSorted(arr)) {
                    System.out.println("Array ist sortiert.");
                } else {
                    System.out.println("Fehler: Array ist nicht sortiert!");
                }

                System.out.println("Benoetigte Zeit in Millis: " + msecs);
            }
            else {
            System.out.println("Zu viel Argumente oder kein Argument");
            }
        }
        catch (RuntimeException e){System.out.println("Fehler: Korrekter Aufruf: 'java Quicksort int n' ");}
    }


}
