import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr;
        float input;
        double timeInMsecs;
        if (args.length == 0){     // Erste Aufgabe.
            arr = BubbleSort.beffuellung(50000);
            timeInMsecs = BubbleSortSuche.bubbleSort(arr);      // Berechnung der Laufzeit.
            System.out.println("Benoetigte Zeit fuer 50000 Elemente: " + String.valueOf(timeInMsecs/1000.0) + "s");
        }
        if (args.length == 1) {         // Nur ein Argument ist eingegeben.
            input = Float.parseFloat(args[0]);  // Speichern des Argumentes.
            if (input < 0) {
                System.out.println("Zeit muss nicht negativ sein!");
            } else {
                assert (input > 0): "Zeit ist negativ";
                int size = 500;
                double zeit = 0;                    // Zeit ist 0 zugewiesen,damit in die While Schleife reigehen wird.
                while (zeit < input) {
                    size = 2 * size;                // Laenge des Feldes muss immer verdoppelt werden,solange die Bedingung nicht verletzt.
                    arr = BubbleSort.beffuellung(size); // Beffuellung des Feldes.
                    zeit = BubbleSortSuche.bubbleSort(arr); // Sortierung des Feldes und Berechnung der Laufzeit.
                    System.out.println("Size: " + size + "," + "Time: " + zeit + "s");
                }
                System.out.println("Starte tertiaere Suche:");
                size = BubbleSortSuche.ternary(size / 2, size, input);  // Ausgabe der gesuchten Laenge.
            }
        }
        else{
            System.out.println("FEHLER: Zu wenig oder zu viele Argumente! ");
        }
    }
}
