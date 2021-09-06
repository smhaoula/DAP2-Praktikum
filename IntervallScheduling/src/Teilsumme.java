public class Teilsumme {

    int start,ende;
    long summe;

    public Teilsumme(int start,int ende,long summe){
        this.start = start;
        this.ende = ende;
        this.summe = summe;
    }

    public int getStart(){
        return this.start;
    }

    public int getEnde(){
        return this.ende;
    }

    public long getSum(){
        return this.summe;
    }

    @Override
    public String toString(){
        return "Start: " + start + " , " + " Ende: " + ende + " , " + "Summe: " + summe;
    }



}
