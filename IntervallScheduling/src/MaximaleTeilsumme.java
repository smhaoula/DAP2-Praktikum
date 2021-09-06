import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MaximaleTeilsumme {

    public static Teilsumme maxTeilsumme(int[] array) {
        int start = 0, ende = 0, current = 0, max = Integer.MIN_VALUE, index = 0;
        for (int i = 0; i < array.length; i++) {
            current += array[i];
            if (max < current) {
                max = current;
                start = index;
                ende = i;
            }
            if (current < 0) {
                current = 0;
                index = i + 1;
            }
            assert max >= current : "Current ist groesser als die max";
        }
        return new Teilsumme(start, ende, max);
    }

    public static int[] ListtoArray(ArrayList list) {   // Hilfsmethode.
        assert !(list.isEmpty()): "List ist leer";
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) list.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        try {
            String dateipfad = args[0];
            StringTokenizer st;
            BufferedReader datei = new BufferedReader(new FileReader(dateipfad));       // Erzeugen eines FileReader ,wir lesen von Dateipfad.
            String line = datei.readLine();     // Linie lesen.

            assert line != null : "Die Datei ist leer";

            while (line != null) {
                st = new StringTokenizer(line, ",");    // Erstellt einen String-Tokenizer für den angegebenen String.
                ArrayList list = new ArrayList();
                while (st.hasMoreTokens()) {        //Testet, ob in dieser Tokenisierungszeichenfolge mehr Token verfügbar sind.
                    list.add(Integer.parseInt(st.nextToken())); // Fuegt dieser Token in die List hinzu.
                }
                assert !list.isEmpty() : "List ist leer geblieben";
                System.out.println(MaximaleTeilsumme.maxTeilsumme(MaximaleTeilsumme.ListtoArray(list)));
                line = datei.readLine();        // naechste Linie lesen,falls es gibt.
            }

            datei.close();      // Schließt den Stream und gibt alle damit verbundenen Systemressourcen frei.
        } catch (FileNotFoundException notFoundException) {
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        } catch (IOException inputoutputException) {
            System.out.println(inputoutputException.getMessage());
        } catch(NullPointerException nullException) {
            System.out.println("FEHLER: Datei ist leer oder enthaellt eine leere Zeile.");
        } catch (Exception e) {
            System.out.println("FEHLER: Korrekter Aufruf: java MaximaleTeilsumme *.csv");
        }
    }

}
