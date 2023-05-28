
import com.formdev.flatlaf.FlatDarkLaf;

/**
 *
 * @author NaveenB2004
 */
public class Updater {

    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            new process.process().installer();
        } catch (Exception e) {
            System.out.println(e);
            new process.process().checkUpdates();
            FlatDarkLaf.setup();
        }
    }
}
