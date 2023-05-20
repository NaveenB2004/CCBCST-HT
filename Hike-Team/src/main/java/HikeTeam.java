
import com.formdev.flatlaf.FlatDarkLaf;

/**
 *
 * @author NaveenB2004
 */
public class HikeTeam {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Main.SplashScreen().setVisible(true);
        Main.SplashScreen.disposeVar.setText("dispose");
        new Main.Home().setVisible(true);
    }
}
