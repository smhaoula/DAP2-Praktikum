import java.security.Signature;

public class Line {

    Point p1,p2;

    public Line(Point p1,Point p2){     // Konstruktor erstellen.
        this.p1 = p1;
        this.p2 = p2;
    }

    public int side(Point p){       // ob eine Punkt links,rechts oder genau auf der Linie liegt.
        double x = p.get(0); double y = p.get(1);
        double xs = this.p1.get(0); double ys = this.p1.get(1);
        double xe = this.p2.get(0); double ye = this.p2.get(1);
        return (int)(Math.signum((x - xs) * (ye - ys) - (y - ys) * (xe - xs)));
    }

}
