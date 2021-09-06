import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ShortestCommonSuperstring {

    public static String generateRandomString() {
        String alphabet = "ABCD";
        Random numberGenerator = new java.util.Random(); StringBuilder builder = new StringBuilder();
        int length = 3 + numberGenerator.nextInt(4); while (length-- > 0) {
            int randomIdx = numberGenerator.nextInt(alphabet.length());
            builder.append(alphabet.charAt(randomIdx)); }
        return builder.toString();
    }

    public static int StringOverlap(String a,String b){
        int m = a.length();
        int n = b.length();
        int max = 0;
        for (int i = 0;i < Math.min(m,n); i++){
            if (a.substring(m - i,m).equals(b.substring(0,i))){
                max = i;
            }
        }
        return max;
    }

    public static int matchtwostrings (String x,String y, StringBuilder builder){
        int max = 0;
        int richtung = StringOverlap(x,y);
        int gegenrichtung = StringOverlap(y,x);
        if (richtung > gegenrichtung){
            max = richtung;
            builder.setLength(0);
            builder.append(x).append(y.substring(richtung, y.length()));
        }
        else {
            max = gegenrichtung;
            builder.setLength(0);
            builder.append(y).append(x.substring(gegenrichtung,x.length()));
        }
        return max;
    }

    public static String[] beffuellung(int n){
        String []arr = new String[n];
        for (int i = 0;i < n;i++){
            arr[i] = generateRandomString().toUpperCase();
        }
        return arr;
    }

    public static ArrayList<String> clon(String[] arr){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0;i < arr.length;i++){
            list.add(arr[i]);
        }
        return list;
    }
    public static String Superstring(ArrayList<String> list) {

        int size = list.size();
        while (size != 1) {

            ///////////////////////////////////////
            int max = Integer.MIN_VALUE;
            int x = 0;int y = 0;
            String ergebnis = "";
            ///////////////////////////////////////

            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {

                    StringBuilder stringb = new StringBuilder();
                    int berechnetemax = matchtwostrings(list.get(i),list.get(j),stringb);

                    if (berechnetemax > max){
                        max = berechnetemax;
                        ergebnis = stringb.toString();
                        x = i;
                        y = j;
                    }
                }
            }

            size --;
            if (max == Integer.MIN_VALUE) {
                list.set(0, list.get(0) + list.get(size));
            }
            else {
                System.out.println("Ersetze " + list.get(x) + " und " + list.get(y) + " durch " + ergebnis);
                list.set(x, ergebnis);
                list.remove(y);
                System.out.println(list.toString());
            }
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        try {
            if (args.length == 1) {     // Nur ein Argument.
                int argument = Integer.parseInt(args[0]);
                String string = args[0];
                if (argument > 1) {
                    String[] arr = ShortestCommonSuperstring.beffuellung(argument);        // Beffuellung des Arrays.
                    ArrayList<String> list = ShortestCommonSuperstring.clon(arr);           // Kopieren des Arrays in List.
                    System.out.println("Superstring " + ShortestCommonSuperstring.Superstring(list) + " mit Laenge " + list.get(0).length() + " gefunden");   // Ausgabe.
                }
                else {
                    System.out.println(string);
                }

            } else if (args.length <= 10) {        // Anzahl der Strings kleiner gleich 10.
                String[] arr = new String[args.length];
                for (int i = 0; i < args.length; i++) {
                    arr[i] = args[i].toUpperCase();
                }
                System.out.println(Arrays.toString(arr));
                ArrayList<String> list = ShortestCommonSuperstring.clon(arr);
                System.out.println(ShortestCommonSuperstring.Superstring(list));
                System.out.println("Superstring " + list.get(0) + " mit Laenge " + list.get(0).length() + " gefunden");
            } else {
                String[] arr = new String[args.length];
                for (int i = 0; i < args.length; i++) {
                    arr[i] = args[i].toUpperCase();
                }
                System.out.println(Arrays.toString(arr));
                ArrayList<String> list = ShortestCommonSuperstring.clon(arr);
                System.out.println("Superstring " + ShortestCommonSuperstring.Superstring(list) + " mit Laenge " + list.get(0).length() + " gefunden"); // direkt Ausgabe der Ergebnis weil mehr als 10 Argumente.
            }
        }
        catch (NumberFormatException e){
            System.out.println("FEHLER: Bitte geben Sie gueltige Argumente");
        }
    }
}
