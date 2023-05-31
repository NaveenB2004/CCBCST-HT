
/**
 *
 * @author NaveenB2004
 */
public class Updater {

    public static void main(String[] args) {
        if (args[0].isEmpty()) {
            new process.process().installer();
        } else {
            new process.process().checkUpdates();
        }
    }
}
