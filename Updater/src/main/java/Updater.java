
import com.formdev.flatlaf.FlatDarkLaf;

/**
 *
 * @author NaveenB2004
 */
public class Updater {

    public static void main(String[] args) {
        String installer = args[0];
        if (installer.equals("install")) {
            new process.process().installer();
        } else {
            new process.process().checkUpdates();
            FlatDarkLaf.setup();
        }
    }
}
