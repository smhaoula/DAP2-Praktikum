import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyException;

public class Main {

    public static void main(String[] args) throws KeyException, IndexOutOfBoundsException, TooManyElementsException {
        try {
            if (args.length == 1){
                Graph graph = Graph.fromFile(args[0]);

                System.out.println(graph.toString());
            }
        }catch (FileNotFoundException notFoundException){
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        }catch (IOException inputoutputException){
            System.out.println(inputoutputException.getMessage());
        }catch(NullPointerException nullException) {
            System.out.println("FEHLER: Datei ist leer oder enthaellt eine leere Zeile.");
        }catch (Exception e) {
            System.out.println("FEHLER: Korrekter Aufruf: java GraphApplication *.graph");
        }
    }}
