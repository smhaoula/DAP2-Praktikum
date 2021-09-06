
public class BubbleSortSuche {
    public static double bubbleSort(int[] array){
        double zeit;
        double tStart, tEnd;
        tStart = System.currentTimeMillis();
        BubbleSort.bubbleSort(array);
        // Ende der Messung
        tEnd = System.currentTimeMillis();
        zeit = (tEnd - tStart) / 1000.0;
        return zeit;
    }
    public static int ternary(int min,int max,double time){
        int first = min + (max - min) / 3;                  //Ersten Bereich bestimmen.
        int second = max - (max - min) / 3;                 //Zweiten Bereich bestimmen.
        int []arr = BubbleSort.beffuellung(first);          // Beffuelung des Feldes.
        double zeit = BubbleSortSuche.bubbleSort(arr);      // Sortieren des Feldes und Bestimmung von Laufzeit.
        System.out.println("Anzahl Elemente: " + String.valueOf(first) + "; Benoetigte Zeit: " + zeit + "s");
        if (Math.abs(zeit - time) <= 0.1){      // Ueberpruefen,ob die Differenz zwischen der berechneten Laufzeit und der von Nutzer eingegebene Laufzeit weniger als 0,1s ist.
            return first;           // Ausgabe der Feldgroesse.
        }
        else if (zeit > time){
            return ternary(min,first - 1,time);     // Die Suche erfolgt im ersten Bereich.
        }
        else if (zeit < time){
            return ternary(second + 1,max,time);    // Die Suche erfolgt im dritten Bereich.
        }
            return ternary(first + 1,second - 1,time);  // Die Suche erfolgt im zweiten Bereich.
    }
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
