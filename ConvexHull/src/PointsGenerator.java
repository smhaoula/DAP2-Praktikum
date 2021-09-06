import java.util.ArrayList;
public class PointsGenerator {

    double min,max;

    public PointsGenerator(double min,double max){      // Konstruktor erstellen.
        this.min = min;
        this.max = max;
    }

    public ArrayList generate(int n){       // Generate Punkte zwischen min und max.
        ArrayList list = new ArrayList();
        for (int i = 0;i < 5; i++){
            java.util.Random generator = new java.util.Random();
            double a = this.min + (this.max - this.min) * generator.nextDouble();
            double b = this.min + (this.max - this.min) * generator.nextDouble();
            Point p = new Point(a,b);
            list.add(p);
        }
        return list;
    }
}
