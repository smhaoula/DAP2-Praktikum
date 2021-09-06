import java.util.ArrayList;

public class ConvexTester {

    public static void main(String[] args) {
        if (args.length == 3){              // 3 Argumente eingegeben.
            double par1 = Double.parseDouble(args[0]);
            double par2 = Double.parseDouble(args[1]);
            int par3 = Integer.parseInt(args[2]);
            ArrayList<Point> p = PointsGenerator.generate(par3);    // par3 Punkte generieren.
            System.out.println(p.toString());                       // Ausgabe der generierten Punkten.
            ArrayList<Point> p2 = ConvexHull.computeHull(p); // Convexhuelle berechnen .
            System.out.println(p2.size());                          // Anzahl der Punkte berechneter Convexhuelle
            System.out.println(p2.toString());                      // Ausgabe berechneter Convexhuelle
            if (p.size() == p2.size()){
                System.out.println("Alle Punkte sind in der Huelle");
            }
            else {
                System.out.println("x Punkte liegen nicht in der Huelle");
            }
        }
        else{
            System.out.println("FEHLER: Zu viel oder zu wenig Argument");
        }
    }
}
