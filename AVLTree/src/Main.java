import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 1 || args.length == 2) {
                String dateipfad = args[0];
                StringTokenizer st;
                BufferedReader datei = new BufferedReader(new FileReader(dateipfad));
                String line = datei.readLine();
                assert line != null : "Die Datei ist leer";

                while (line != null){
                    st = new StringTokenizer(line, ",");
                    ArrayList list = new ArrayList();
                    while (st.hasMoreTokens()) {
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                    AVLTree tree = new AVLTree(AVLTree.ListtoArray(list));
                    System.out.println("Hoehe: " + AVLTree.getHeight(tree));
                    if (args.length == 2){
                        String travers = args[1];
                        switch (travers){
                            case "in" : postOrder(tree); break;
                            case "pre" : tree.preOrder(); break;
                            case "post" : tree.postOrder(); break;
                        }
                        System.out.println();
                    }
                    else {
                        AVLTree.inOrder(tree);
                        System.out.println();
                    }
                    line = datei.readLine();
                    System.out.println("----------");
                }
                datei.close();
            }
        }catch (FileNotFoundException notFoundException){
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        }catch (IOException inputoutputException){
            System.out.println(inputoutputException.getMessage());
        }catch(NullPointerException nullException) {
            System.out.println("FEHLER: Datei ist leer oder enthaellt eine leere Zeile.");
        }catch (Exception e) {
            System.out.println("FEHLER: Korrekter Aufruf: java SearchTreeApplication *.csv | java SearchTreeApplication *.csv traversierung");
        }
    }
}
