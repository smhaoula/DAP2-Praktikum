public class Main {

    public static void main(String[] args) {
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
}
