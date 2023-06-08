
/**
 *
 * @author NaveenB2004
 */
public class Updater {
    
    public static void main(String[] args) {
        String y = args[0];
        if (y.equals("install")) {
            new process.process().installer();
        } else {
            new process.process().checkUpdates();
        }
        
    }
}
