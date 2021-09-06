import javafx.print.Collation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConvexHull {

    public ArrayList<Point> convexhull(ArrayList<Point> p){
        Collections.sort(p,new Comparaison());              // die List erst sortieren nach x.
        ArrayList<Point> ausgabe = new ArrayList<>();       // leere List erstellen.
        for (int i = 0;i < p.size(); i++){                  // Implementierung wie in der Vorlesung.
            for (int j = i + 1;j < p.size(); j++){
                boolean valid = true;
                Line a = new Line(p.get(i),p.get(j));
                for (int k = 0; k < p.size(); k++){
                    if (!(p.get(i).equals(p.get(k))) && !(p.get(j).equals(p.get(k))) && a.side(p.get(k)) == 1){
                        valid = false;
                    }
                }
                if (valid == true){
                    ausgabe.add(p.get(i));
                    ausgabe.add(p.get(j));
                }
            }
        }
        return ausgabe;
    }

    public double angleberechnen(Point A,Point B,Point E){
        double slope1 = (B.get(1) - A.get(1)) / (B.get(0) - A.get(0));      //Winkel berechnen
        double slope2 = (E.get(1) - B.get(1)) / (E.get(0) - B.get(0));
        double angle = Math.atan((slope1 - slope2) / (1 - (slope1 * slope2)));
        return angle;
    }

    public boolean pointoverline(Line l,ArrayList<Point> p){    // ueberpruefen,ob eine Punkt auf der Linie liegt.
        for (int i = 0;i < p.size(); i++){
            if (l.side(p.get(i)) == 1)
                return true;
        }
        return false;
    }

    public boolean pointunderline(Line l,ArrayList<Point> p){   // ueberpruefen,ob eine Punkt unter der Linie liegt.
        for (int i = 0;i < p.size(); i++){
            if (l.side(p.get(i)) == -1)
                return true;
        }
        return false;
    }

    public ArrayList<Point> SucheObereStrecke(ArrayList<Point> a, ArrayList<Point> b){  //Suche nach der oberen Strecke
        Collections.sort(a,new Comparaison());
        Collections.sort(b,new Comparaison());
        Point B = a.get(a.size() - 1);
        Point A = a.get(a.size() - 2);
        Point C = new Point();
        Point E = b.get(0);
        Point D = b.get(b.size() - 1);
        Point F = new Point();
        Line line = new Line(B,E);
        while (pointoverline(line,a) || pointoverline(line,b)){
            if (angleberechnen(A,B,E) < 180) {

                B = A;
                A = a.get(a.indexOf(B) - 1);
                C = a.get(a.indexOf(B) + 1);
            }
            if (angleberechnen(B,E,F) < 180) {

                E = F;
                D = a.get(a.indexOf(E) - 1);
                F = a.get(a.indexOf(E) + 1);
            }
                line = new Line(B, E);
                for (int i = 0;i< b.size();i++){
                    a.add(b.get(i));
                }
        }
        return a;
    }

    public Line SucheUntereStrecke(ArrayList<Point> a, ArrayList<Point> b){ // Suche nach der unteren Strecke
        Point B = a.get(a.size() - 1);
        Point A = a.get(a.size() - 2);
        Point C = new Point();
        Point E = b.get(0);
        Point D = b.get(b.size() - 1);
        Point F = new Point();
        Line line = new Line(B,E);
        while (pointunderline(line,a) || pointunderline(line,b)){
            if (angleberechnen(A,B,E) < 180) {

                B = A;
                A = a.get(a.indexOf(B) - 1);
                C = a.get(a.indexOf(B) + 1);
            }
            if (angleberechnen(B,E,F) < 180) {

                E = F;
                D = a.get(a.indexOf(E) - 1);
                F = a.get(a.indexOf(E) + 1);
            }
            line = new Line(B,E);
        }
        return line;
    }

    public ArrayList<Point> merge(ArrayList<Point> a,ArrayList<Point> b){   // koennen es nicht implementieren.
        SucheObereStrecke(a,b);
        SucheUntereStrecke(a,b);
        return a;
    }


    public ArrayList<Point> computeHull(ArrayList<Point> p) {
        if (p.size() <= 2){     // Abruchkriterium.
            convexhull(p);
        }
            int mitte = (int) (Math.floor(p.size() / 2));       // mitte suchen.
            ArrayList<Point> a = computeHull(new ArrayList<Point>(p.subList(0,mitte)));
            ArrayList<Point> b = computeHull(new ArrayList<Point>(p.subList(mitte + 1,p.size() - 1)));
            return merge(a,b);
    }

}