
/**
 *
 * @author NaveenB2004
 */
public class Updater {

    public static void main(String[] args) {
        try {
            new process.process().installer();
        } catch (Exception e) {
            new process.process().checkUpdates();
        }
    }
}
