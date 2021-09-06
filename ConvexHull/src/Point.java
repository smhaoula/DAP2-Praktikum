import java.io.PrintStream;
import java.lang.String;
import java.util.Comparator;

public class Point {    // Kontruktor erstellen.

    double []speicher;

    public Point(double ... v){
        if (v.length == 0 || v == null){
            throw new IllegalArgumentException("Muss gueltige Argumente eingeben");
        }
        speicher = v;
    }

    @Override
    public boolean equals(Point e){     // ob zwei Objekten gleich sind.
        return e == this;
    }

    @Override
    public String toString(){           // Ausgabe der Kordinaten einer Punkt mit maximal 4 Nachkommazahlen.

        String s = "";
        for (int i = 0;i < speicher.length;i++){
           s += String.format("%.4f%n",speicher[i]) + " ";
        }
        return s;
    }

    public double get(int i){           // Ausgabe der i-ten Kordinaten einer Punkt.
        return speicher[i];
    }
}
class Comparaison implements Comparator<Point>{     // Vergleich Objekten von Typ Point.

    public int compare(Point a,Point b){
        return a.get(0) > b.get(0) ? 1 : a.get(0) < b.get(0) ? -1 : -1;
    }
}

