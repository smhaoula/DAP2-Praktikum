import java.util.ArrayList;

public class MinHeap {

    private HeapElement []array;
    private int idindex [];         //Zum Speichern von Indexen.
    private int heapSize;
    private int maxid;

    public MinHeap(int n,int k) {
        this.idindex = new int [n + 1];
        this.maxid = k;
        this.heapSize = 0;
        array = new HeapElement[n + 1];
        array[0] = new HeapElement(-1,-1);      // Weil Heap vom Index 1 starten soll.
    }

    public int parent(int i){
        return i / 2;
    }

    public int left(int i){
        return 2 * i;
    }

    public int right(int i){
        return 2 * i + 1;
    }

    private void swap(int i, int j)             //Hilfsmethode zum Swapen zwei HeapElemente.
    {
        int tmp;
        tmp = array[i].getPrio();
        array[i].prio = array[j].getPrio();
        array[j].prio = tmp;
        int tmp1;
        tmp1 = array[i].getId();
        array[i].id = array[j].getId();
        array[j].id = tmp1;

    }

    public void heapify(int i){
        int l = left(i);
        int r = right(i);
        int k = i;
        if (l < heapSize && array[l].getPrio() < array[i].getPrio())
            k = l;
        if (r < heapSize && array[r].getPrio() < array[k].getPrio())
            k = r;
        assert k != i: "AssertionTest";
        if (k != i){
            swap(i,k);
            heapify(k);
        }
    }

    public void insert(HeapElement element){
        if (heapSize >= array.length){
            return;
        }
        assert element.id <= maxid : "Element kann nicht hinzigefuegt werden";
        if (element.id <= maxid) {      // Hoeher Wert von JobID soll die maxID nicht ueberschritten.
            System.out.println("Element (" + element.getPrio() + ", " + element.getId() + " ) wurde zum Heap hinzugefuegt");
            heapSize++;
            array[heapSize] = element;
            int current = heapSize;
            while (array[current].getPrio() < array[parent(current)].getPrio()) {
                swap(current, parent(current));
                current = parent(current);
            }
            //heapify(1);
            neworder();
        }
    }

    public HeapElement extractMin(){
        assert heapSize >= 1 :"Heap ist leer";
        if (heapSize == 1) {
            heapSize--;
            assert heapSize == 0: "Heap enthaelt noch Elemente!!";
            HeapElement min = array[1];
            array[1] = null;
            return min;
        }
        HeapElement first = array[1];
        array[1] = array[heapSize];
        array[heapSize] = null;
        heapSize--;
        assert heapSize >= 1 : "Heap enthaelt kein Element";
        heapify(1);
        neworder();
        return first;
    }

    public void remove(int jobID){
        int k = idindex[jobID];
        array[k].id = array[1].getId();
        array[k].prio = array[1].getPrio();
        extractMin();
        neworder();
        System.out.println("Job mit der ID " + jobID + " wurde entfernt");
    }

    public void decreasePrio(int jobID, int prio){
        int index = idindex[jobID];             // Direkter Zugriff.
        if (prio < array[index].getPrio()){
            array[index].prio = prio;
            System.out.println("Der Prioritaetswert von Job " + jobID + " wurde auf " + prio + " reduziert");
            heapify(1);             // Heap soll wieder gebildet werden.
            neworder();
        }
    }

    public void printHeap(){
        for (int i = 1;i < heapSize;i++){
            System.out.print("(" + array[i].getPrio() + ", " + array[i].getId() + ")" + ",");
        }
        System.out.print(" (" + array[heapSize].getPrio() + "," + array[heapSize].getId() + ")");
    }

    public void neworder(){                 // Hilfsmethode zum Aktualisieren der Indexen,falls ein Element entfernt ,extrahiert oder hinzugeguegt wurde.
        for (int i = 1;i <= heapSize;i++){
            idindex[array[i].getId()] = i;
        }
    }
}