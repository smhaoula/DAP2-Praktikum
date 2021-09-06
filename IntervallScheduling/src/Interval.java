import java.util.Comparator;

public class Interval{

    int start;
    int end;

    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    @Override
    public String toString()
    {
        return "[" + start + "," + end + "]";
    }

}
class Comparaison implements Comparator<Interval> {     // Vergleich Objekten von Typ Interval.

    public int compare(Interval interval1,Interval interval2){
        return interval1.getEnd() > interval2.getEnd() ? 1 : interval1.getEnd() < interval2.getEnd() ? -1 : 0;
        //return interval1.getStart() > interval2.getStart() ? 1 : interval1.getStart() < interval2.getStart() ? -1 : 0;
    }
}


