
/**
 *
 * @author NaveenB2004
 */
public class Updater {

    public static void main(String[] args) {
        int x = 0;
        try {
            String y = args[0];
            x++;
        } catch (Exception e) {
            System.out.println(e);
        }
        if (x != 0) {
            new process.process().installer();
        } else {
            new process.process().checkUpdates();
        }
    }
}
