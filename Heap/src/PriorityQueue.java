import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PriorityQueue {

    public static void main(String[] args) {
/*
      try {
            if (args.length == 3) {
                int par1 = Integer.parseInt(args[0]);
                int par2 = Integer.parseInt(args[1]);
                if (par1 > 0 && par2 > 0) {
                    String path = args[2];
                    MinHeap heap = new MinHeap(par1, par2);
                    StringTokenizer st;
                    BufferedReader datei = new BufferedReader(new FileReader(path));
                    String line = datei.readLine();
                    assert line != null : "Die Datei ist leer";
                    while (line != null) {
                        st = new StringTokenizer(line, ",");         // Erstellt einen String-Tokenizer für den angegebenen String.
                        ArrayList<String> speicher = new ArrayList<>();
                        while (st.hasMoreTokens()) {
                            speicher.add(st.nextToken());
                        }
                        assert !speicher.isEmpty() : "List ist leer geblieben";
                        String operation = speicher.get(0);
                        switch (operation) {        //Fallunterscheidung.
                            case "A":
                                HeapElement element = new HeapElement(Integer.parseInt(speicher.get(1)), Integer.parseInt(speicher.get(2)));
                                heap.insert(element);
                                break;
                            case "E":
                                HeapElement remove = heap.extractMin();
                                System.out.println("Element (" + remove.getPrio() + ", " + remove.getId() + ") wurde extrahiert");
                                break;
                            case "R":
                                heap.remove(Integer.parseInt(speicher.get(1)));
                                break;
                            case "D":
                                heap.decreasePrio(Integer.parseInt(speicher.get(1)), Integer.parseInt(speicher.get(2)));
                                break;
                            default:
                                System.out.println("Geben Sie eine gueltige Buchstabe");
                        }
                        heap.printHeap();       // Heap ausgeben.
                        System.out.println();
                        line = datei.readLine();         // naechste Linie lesen,falls es gibt.
                    }
                    datei.close();      // Schließt den Stream und gibt alle damit verbundenen Systemressourcen frei.
                }
            }
            else {
                System.out.println("FEHLER : Geben Sie genau 3 Argumente.");
            }
        } catch (FileNotFoundException notFoundException){
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        }catch (IOException inputoutputException){
            System.out.println(inputoutputException.getMessage());
        }catch(NullPointerException nullException) {
            System.out.println("FEHLER: Datei ist leer oder enthaellt eine leere Zeile.");
        }catch (Exception e) {
            System.out.println("FEHLER: Korrekter Aufruf: java PriorityQueue int int heapOperations.csv");
        }
*/
    }
}
