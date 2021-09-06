import javafx.util.Pair;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
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
        int richtung, gegenrichtung;
        int max = 0;
        int x = 0;
        int y = 0;
        String word = "";
        int size = list.size();
        while (size > 1) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    richtung = StringOverlap(list.get(i), list.get(j));
                    gegenrichtung = StringOverlap(list.get(j), list.get(i));
                    if ((richtung > gegenrichtung) && (richtung > max)) {
                        max = richtung;
                        x = i;
                        y = j;
                    }
                    else if ((gegenrichtung > richtung) && (gegenrichtung > max)){
                        max = gegenrichtung;
                        x = j;
                        y = i;
                    }
                }
            }
            if (max == 0) {
                return list.toString();
            }
            else {
                word = list.get(x) + list.get(y).substring(max, list.get(y).length());
                list.remove(x);
                list.remove(y);
                list.add(word);
                max = 0;
            }
            size--;
        }
        return list.toString();
    }
}
