
/**
 *
 * @author NaveenB2004
 */
public class Updater {

    public static void main(String[] args) {
        try {
            String y = args[0];
            new process.process().installer();
        } catch (Exception e) {
            new process.process().checkUpdates();
        }

    }
}
