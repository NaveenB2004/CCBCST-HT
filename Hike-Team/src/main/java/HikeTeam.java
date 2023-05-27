
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author NaveenB2004
 */
public class HikeTeam {

    public static void main(String[] args) {
        if (new File("theme.ini").exists()) {
            try (Stream<String> lines = Files.lines(Paths.get("theme.ini"))) {
                String theme = lines.skip(0).findFirst().get();
                switch (theme) {
                    case "0" -> {
                        FlatDarkLaf.setup();
                    }
                    case "1" -> {
                        FlatLightLaf.setup();
                    }
                    case "2" -> {
                        try {
                            UIManager.setLookAndFeel(
                                    UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                                | UnsupportedLookAndFeelException ex) {
                            System.out.println("0003" + ex);
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            FlatDarkLaf.setup();
        }
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
