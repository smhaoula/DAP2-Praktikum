import apple.laf.JRSUIConstants;

import java.util.Arrays;
import java.util.function.Function;

public class KangarooJump {

    private int targetPos; /* Target position of the kangaroo */
    private int maxPos; /* Highest index of a tile */
    private int maxJumps; /* Maximum number of jumps */
    private int[] jumpSizes; /* List of possible jump sizes on the given walkway */
    private Directions[][][] dynArr; /* Dynamic programming array to find the solution */

    public KangarooJump(int targetPos, int maxPos, int maxJumps) {
        this.targetPos = targetPos;
        this.maxPos = maxPos;
        this.maxJumps = maxJumps;
        initJumpSizes();
        /* Initialize whenever you think fits best */
        this.dynArr = null;
    }

    /* Compute a list of powers of 2 */
    private void initJumpSizes() {
        int maxJumpSizeIdx = 1;
        int maxPosCpy = maxPos;
        while (maxPosCpy > 0) {
            maxPosCpy = maxPosCpy >> 1;
            maxJumpSizeIdx++;
        }
        jumpSizes = new int[maxJumpSizeIdx];
        for (int i = 0; i < maxJumpSizeIdx; i++) {
            jumpSizes[i] = 1 << i;
        }
    }

    /* Initialize the dynamic programming array */
    private void initDynArray() {
        /* TODO */
        dynArr = new Directions[maxJumps][maxPos][jumpSizes.length];    //passende Groesse anlegen.
        for (int j = 0;j < maxJumps;j++){       // Durchlauf beginnen.
            for (int i = 0;i < maxPos;i++){
                for (int s = 0;s < jumpSizes.length;s++){
                    if ((i + jumpSizes[s] <= maxPos - 1) && (i - jumpSizes[s] >= 0)){
                        dynArr[j][i][s] = Directions.BOTH;      //Fliese ist von beiden Richtungen erreichbar.
                    }
                    else if (i + jumpSizes[s] <= maxPos - 1){
                        dynArr[j][i][s] = Directions.RIGHT; //Fliese ist von rechts erreichbar.
                    }
                    else if (i - jumpSizes[s] >= 0){
                        dynArr[j][i][s] = Directions.LEFT;  //Fliese ist von links erreichbar.
                    }
                    else {
                        dynArr[j][i][s] = Directions.NONE;      //Fliese ist nicht erreichbar.
                    }
                }
            }
        }
    }

    /*
     * Fill the dynamic programming array completely or until
     * the target position has been reached.
     */
    private int fillDynArray() {
        /* TODO */
        initDynArray();
        int j =1,i = 0,s = 0;       // indexe initialisieren.
        while (j < maxJumps && i != targetPos){     // Anzahl von Spruenge darf nicht maxspruenge ueberschritten.
            while (i < maxPos) {            // 3D Array dynamisch auffuellen.
                while (s < jumpSizes.length){
                    if ((i + jumpSizes[s] < maxPos - 1) && (i - jumpSizes[s] >= 0)){
                        add(j,i,s,Directions.BOTH);
                    }
                    else if ((i + jumpSizes[s] < maxPos - 1) && (i < targetPos)){
                        add(j,i,s,Directions.RIGHT);

                    }
                    else if (i + jumpSizes[s] > 0 && i > targetPos){
                        add(j,i,s,Directions.LEFT);     // der alte Wert aktualisieren.
                    }
                    else if (i == targetPos){   // im Fall sol j ausgegeben werden.also min Jump
                        System.out.println("Mindestens notwendige Spruenge: " + j);
                        return j;
                    }
                    else {
                        add(j,i,s,Directions.NONE);
                    }
                    s++;
                }
                s = 0;
                i++;
            }
            i = 0;
            j++;
        }
        System.out.println("Kein Pfad moeglich!");
        return -1;
    }

    /*
     * Finds a path from the filled dynamic programming array, assuming
     * that the target position has been reached on the specified jump.
     */
    private String findPath(int jump) {
        /* TODO */
            String path = "";
            int index = targetPos;
            while (jump > 0) {
                assert jump > 0:"mehr Durchlauf";
                for (int s = 0; s < jumpSizes.length; s++) {
                    if (dynArr[jump][index][s].hasLeft()) {
                        path += "+ ";       // dh von lnks nach rechts.
                        index -= jumpSizes[s];    // nach vorheriger Fliese suchen.
                    } else if (dynArr[jump][index][s].hasRight()) {
                        path += "- ";       // dh von rechts nach links.
                        index++;
                    }
                    System.out.println("Sprung " + jump + "nach " + index);
                }
                jump--;
            }
            if (path.length() != 0){
                return path;
            }
        return null;
    }

    /*
     * Computes a String representing the path from position 0 to
     * the target position or null if non such path is possible.
     */
    public String computeDirections() {
        /* TODO */
        int jump = fillDynArray();
        return findPath(jump);
    }

    /*
     * Add a set of direction options to the options already
     * available in some cell of the dynamic programming array.
     */
    private void add(int x, int y, int z, Directions d) {
        dynArr[x][y][z] = dynArr[x][y][z].add(d);
    }

    public static void main(String[] args) {
        /* TODO */
        try {
            if (args.length == 3) {     // Die Anzahl der Argumente muss genau 3 sein.
                int target = Integer.parseInt(args[0]);
                int maxfliese = Integer.parseInt(args[1]);
                int maxsprung = Integer.parseInt(args[2]);
                if (target > 0 && maxfliese > target && maxsprung >= 1){
                    KangarooJump kangaru = new KangarooJump(target,maxfliese,maxsprung); // Objekt erstellen.
                    kangaru.computeDirections();        // Methode aufruf.
                }
            }
        } catch (RuntimeException run){System.out.println("Fehler! Anzahl der Argumente " + run.getMessage());
        }//catch (NumberFormatException e){
           // System.out.println("FEHLER: Bitte geben Sie gueltige Argumente");}
        catch (Exception e ){System.out.println("FEHLER: Bitte geben Sie gueltige Argumente");}
    }

    /*
     * Enum to help you keep track of direction options in
     * the dynamic programming array.
     */
    public static enum Directions {
        NONE('0'), LEFT('-'), RIGHT('+'), BOTH('x');

        /* Display character for printing */
        private char s;

        Directions(char s) {
            this.s = s;
        }

        /* Checks if left is a viable option */
        public boolean hasLeft() {
            return this == LEFT || this == BOTH;
        }

        /* Checks if right is a viable option */
        public boolean hasRight() {
            return this == RIGHT || this == BOTH;
        }

        /* Adds a given set of direction options and returns the result */
        public Directions add(Directions other) {
            if (this == NONE || other == BOTH)
                return other;
            if (other == NONE || this == BOTH)
                return this;
            if ((this == LEFT && other == RIGHT) || (this == RIGHT && other == LEFT))
                return BOTH;
            return this;
        }

        @Override
        public String toString() {
            return "" + s;
        }
    }
}