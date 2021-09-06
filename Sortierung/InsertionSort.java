import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    public static int[] insertionSort (int [] arr){

        for (int j = 1; j < arr.length ; j++){
            int key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] < key){
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
        return arr;
    }


    public static boolean isSorted(int[] array){

        for (int i = 0; i < array.length-1; i++){
            if (array[i] < array[i+1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            assert (args.length == 1 || args.length == 2) : "Ungueltige Argumente";
            int eingabe = Integer.parseInt(args[0]);
            if (eingabe > 0) {                // Ueberpruefen,ob die Laenge des Feldes grosser Null ist.
                int[] arr = new int[eingabe]; // Erzeugung des Feldes
                if (args.length == 2) {       // Ueberpruefen,ob die zweite Parameter eingegeben wurde.
                    String art = args[1];     // Zwischenspeicer von zweitem Argument.
                    if (art.equals("ab")) {   // von Zeile 40 bis 53 geht's um die Art der Beffuellung und der Beffuellung des Feldes.
                        for (int i = 0; i <arr.length ; i++) {
                            arr[i] = arr.length - i;
                        }

                    } else if (art.equals("auf")) {
                        for (int i = 0; i < eingabe; i++) {
                            arr[i] = i;
                        }

                    } else if (art.equals("rand")) {
                        for (int i = 0; i < eingabe; i++) {
                            arr[i] = (int) (Math.random() * 100);
                        }

                    } else {
                        System.out.println("Bitte geben Sie gueltige Auswahl ein");     // Es geht hier um ungueltige Eingqbe.
                    }
                } else {                                        // Beffuelung des Feldes bei nur einer Eingabe mit zuffaeligen Zahlen.
                    for (int i = eingabe; i > 0; i++) {
                        arr[i] = (int) (Math.random() * 100);
                    }
                }
                long tStart = System.currentTimeMillis();       // Laufzeitrechnen
                if (eingabe <= 100) {                           // Bei hoechstens 100 Elemente soll zwei Zeilen ausgegeben werden(beffueltes Array und sortiertes Array und Ergebnis)
                    System.out.println(Arrays.toString(arr));
                    insertionSort(arr);                         // Aufruf der Methode insertionSort()
                    System.out.println(Arrays.toString(arr));
                }
                insertionSort(arr);
                long tEnd = System.currentTimeMillis();
                long msec = tEnd - tStart;      // Damit die Laufzeit berechnet werden konnte,muss die Differenz berechnet werden.
                if (isSorted(arr)) {            // Ueberpruefen,ob das Feld sortiert ist.
                    System.out.println("Feld ist sortiert!");
                } else {
                    System.out.println("Fehler: Feld ist nicht sortiert");
                }
                System.out.println("Das Sortieren des Arrays hat " + msec + " ms gedauert");        // Die berechnete Laufzeit.
            } else {
                System.out.println("Geben Sie bitte einen Wert groesser als 0");
            }

        }
        catch(NumberFormatException exception){
            System.out.println("Geben Sie einen gueltigen Parameter");
        }
    }
}
