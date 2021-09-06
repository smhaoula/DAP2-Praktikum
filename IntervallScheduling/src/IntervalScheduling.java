import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class IntervalScheduling {

    public static ArrayList<Interval> intervalScheduling (ArrayList<Interval> intervals) {
    // Implementierung wie bei der Vorlesung.
        if (intervals.isEmpty()){
            return null;
        }
        ArrayList<Interval> list = new ArrayList<Interval>();
        list.add(intervals.get(0));
        int j = 0;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).getStart() >= intervals.get(j).getEnd()) {
                list.add(intervals.get(i));
                j = i;
            }
        }
        return list;
    }

    public static int idleTime(ArrayList<Interval> intervals) {     // Leerlaufzeit berechnen.
        int summe = 0;
        for (int i = 0;i < intervals.size() - 1;i++) {
            summe += intervals.get(i + 1).getStart() - intervals.get(i).getEnd();
        }
        return summe;
    }

    public static String ListtoIntervall (ArrayList<Interval> list){
        String ausgabe = "";
        for (int i = 0;i < list.size();i++){
            ausgabe += list.get(i).toString() + " ";
        }
        return ausgabe;
    }

    public static void main(String[] args) {
        try {
            String dateipfad = args[0];     // Eingegebene Pfad speichern.
            StringTokenizer st;
            Interval interval;
            int start, ende;
            ArrayList<Interval> list = new ArrayList<Interval>();
            BufferedReader datei = new BufferedReader( new FileReader( dateipfad ) );   // Erzeugen eines FileReader ,wir lesen von Dateipfad.
            String line = datei.readLine();     // Linie lesen.

            assert line != null : "Die Datei ist leer";

            while (line != null){
                st = new StringTokenizer(line, ",");        // Erstellt einen String-Tokenizer für den angegebenen String.
                start = Integer.parseInt(st.nextToken());
                ende = Integer.parseInt(st.nextToken());
                interval = new Interval(start,ende);
                list.add(interval);     // Das Interval-Objekt hinzufuegen.
                line = datei.readLine();        // naechste Linie lesen,falls es gibt.
            }
            datei.close();      // Schließt den Stream und gibt alle damit verbundenen Systemressourcen frei.
            assert !(list.isEmpty()) : "List ist leer geblieben";
            if (list.size() <= 50){
                System.out.println(IntervalScheduling.ListtoIntervall(list)); // List ausgeben.
                Collections.sort(list,new Comparaison());   // List nach dem Attribut ende Sortieren.
                System.out.println(IntervalScheduling.ListtoIntervall(list));        // List nach dem Sortieren ausgeben.
            }
            else{
                Collections.sort(list,new Comparaison());       // List nach dem Attribut ende Sortieren.
            }
            ArrayList<Interval> newlist = IntervalScheduling.intervalScheduling(list);      // Die Methode intervalScheduling aufrufen.
            System.out.println(IntervalScheduling.ListtoIntervall(newlist));     // Ergebnis ausgeben.
            System.out.println("Leerlaufzeit: " + IntervalScheduling.idleTime(newlist));    // Die Methode idleTime aufrufen.
        } catch (FileNotFoundException notFoundException){
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        } catch(NullPointerException nullException) {
            System.out.println("FEHLER: Datei ist leer oder enthaellt eine leere Zeile.");
        }catch(NoSuchElementException noElementException) {
            System.out.println("FEHLER: Mindestens eine Zeile ist in falschem Format.");
        } catch (IOException inputoutputException){
            System.out.println(inputoutputException.getMessage());
        }
    }
}
