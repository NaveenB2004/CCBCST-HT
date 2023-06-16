
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author NaveenB2004
 */
public class HikeTeam {

    public static void main(String[] args) {
        // set theme
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
                            Logger.getLogger(HikeTeam.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(HikeTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FlatDarkLaf.setup();
        }
        new Main.SplashScreen().setVisible(true);

        // apply version changes
        new Main.VerChanges().callChanges();

        // check & install updates when available
        new Main.Updates().update();

        // get db status
        if (!new File("database.db").exists()) {
            new Main.Database().mkdb();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Logger.getLogger(HikeTeam.class.getName()).log(Level.SEVERE, null, e);
        }
        Main.SplashScreen.disposeVar.setText("dispose");
        new Main.Home().setVisible(true);
    }
}
