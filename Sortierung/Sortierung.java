import java.util.Arrays;

public class Sortierung {
    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        tmpArray = array;                           // Kopieren des Feldes.
        mergeSort(array, tmpArray, 0, array.length - 1);
    }

    public static void mergeSort(int[] arr, int[] tmpArr, int l, int r) {
        if (l < r) {
            int mitte = (int) (Math.floor((l + r) / 2));
            mergeSort(arr, tmpArr, l, mitte);
            mergeSort(arr, tmpArr, mitte + 1, r);
            merge(tmpArr, l, mitte, r);             // Benutzen von tmpArray fuer das Zwischenergebnis.
        }
    }

    public static void merge(int[] tmpArr, int l, int mitte, int r) {

        int left[] = new int [mitte - l + 1];       // Erstellen der linke Haelfte des Feldes.
        int right[] = new int [r - mitte];          // Erstellen der rechte Haelfte des Feldes.

        for (int i = 0; i < left.length; i++){      // Elemente kopieren.
            left[i] = tmpArr[l + i];
        }
        for (int j = 0; j < right.length; j++){
            right[j] = tmpArr[mitte + 1 + j];
        }

        int i = 0;                                  // Indexe initialisieren.
        int j = 0;
        int k = l;
        while (i < left.length && j < right.length){        // Sortieren des Feldes absteigend.
            if(left[i] >= right[j]){
                tmpArr[k] = left[i];
                i++;
            }
            else {
                tmpArr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {       // Uebergbliebene Elemente ins Feld kopieren.
            tmpArr[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            tmpArr[k] = right[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        try {
            assert (args.length >= 1 && args.length <= 3) : "Ungueltige Argumente";
            int eingabe = Integer.parseInt(args[0]);        // Laenge des Feldes als int.
            if (eingabe > 0) {
                int[] arr = new int[eingabe];               // Erstellen des Feldes mit Laenge eingabe.
                if (args.length == 1) {
                    for (int i = 0; i < eingabe; i++) {     // Auffuelung des Feldes mit Zuffaeligen Zahlen.
                        arr[i] = (int) (Math.random() * 100);
                    }
                    long tStart, tEnd, msecs;               // Begin der Zeitmessung.
                    tStart = System.currentTimeMillis();
                    System.out.println(Arrays.toString(arr));
                    Sortierung.mergeSort(arr);
                    System.out.println(Arrays.toString(arr));
                    tEnd = System.currentTimeMillis();      //Ende der Zeitmessung.
                    msecs = tEnd - tStart;
                    if (InsertionSort.isSorted(arr)) System.out.println("Feld sortiert!");  // Ueberpruefen,ob das Feld sortiert ist.
                    else System.out.println("Feld NICHT sortiert");
                    System.out.println("Benoetigte Zeit in Millisekunden: " + String.valueOf(msecs));   // Ausgabe der Laufzeit.

                } else if (args.length > 1) {
                    if (args.length > 2) {          // hier kann Argumente mehr als 2 sein.
                        String art = args[2];       // Zwischenspeicher des zweiten Argumentes.
                        switch (art) {              // geht's um die Art der Beffuellung und der Beffuellung des Feldes.
                            case "auf":
                                for (int i = 0; i < arr.length; i++) {
                                    arr[i] = i;
                                }
                                break;
                            case "ab":
                                for (int i = arr.length - 1; i >= 0; i--) {
                                    arr[i] = i;
                                }
                                break;
                            case "rand":
                                for (int i = 0; i < eingabe; i++) {
                                    arr[i] = (int) (Math.random() * 100);
                                }
                                break;
                            default:
                                throw new RuntimeException();
                        }
                    } else {                                    // Argumente sind nur zwei.
                        for (int i = 0; i < eingabe; i++) {     // Auffuelung des Feldes mit Zuffaeligen Zahlen.
                            arr[i] = (int) (Math.random() * 100);
                        }
                    }
                    long tStart, tEnd, msecs;
                    tStart = System.currentTimeMillis();
                    String sort = args[1];
                    switch (sort) {                             // Art der Sortierung merge oder insert.
                        case "merge":
                            System.out.println(Arrays.toString(arr));
                            Sortierung.mergeSort(arr);
                            System.out.println(Arrays.toString(arr));
                            break;
                        case "insert":
                            System.out.println(Arrays.toString(arr));
                            InsertionSort.insertionSort(arr);
                            System.out.println(Arrays.toString(arr));
                            break;
                        default:
                            System.out.println("Auswahl : merge oder insert");
                    }
                    tEnd = System.currentTimeMillis();      //Ende der Zeitmessung
                    msecs = tEnd - tStart;
                    if (InsertionSort.isSorted(arr)) System.out.println("Feld sortiert!");  // Ueberpruefen,ob das Feld sortiert ist.
                    else System.out.println("Feld NICHT sortiert");
                    System.out.println("Benoetigte Zeit in Millisekunden: " + String.valueOf(msecs));   // Ausgabe der Laufzeit.
                }
            }
        }
        catch (Exception e){System.out.println("Fehler! Korrekter Aufruf: Sortierung int [merge | insert [rand | auf | ab]]");}
    }
}

