
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.File;

/**
 *
 * @author NaveenB2004
 */
public class HikeTeam {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Main.SplashScreen().setVisible(true);
        if (!new File("CCBCST.db").exists()) {
            new Main.Database().mkdb();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        Main.SplashScreen.disposeVar.setText("dispose");
        new Main.Home().setVisible(true);
    }
}
