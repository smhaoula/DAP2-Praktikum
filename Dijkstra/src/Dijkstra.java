import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyException;

public class Dijkstra {

    public static void main(String[] args) throws KeyException, IndexOutOfBoundsException, TooManyElementsException{

        try{
            String pfad = args[0];
            int source = Integer.parseInt(args[1]);
            int destination = Integer.parseInt(args[2]);
            Graph graph = Graph.fromFile(pfad);
            DijkstraResult result = new DijkstraResult(graph,source,destination);
            result.dijkstra(graph,source,destination);
            System.out.println(result.toString());

        }catch (FileNotFoundException notFoundException){
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        }catch (IOException inputoutputException){
            System.out.println(inputoutputException.getMessage());
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }catch (KeyException ke){
            System.out.println("KeyException");
        }catch (TooManyElementsException tee){
            System.out.println("TooManyElementsException");
        }catch (Exception e) {
            System.out.println("FEHLER: Korrekter Aufruf: java Dijkstra BspGraphKlein.graph int int");
        }
    }
}
