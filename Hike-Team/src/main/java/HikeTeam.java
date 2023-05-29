
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
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
        String version = "0.2";

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
                            System.out.println(ex);
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

        // install updates when available
        if (new File("CCBCST Hike-Team.nnb").exists()) {
            try {
                new ProcessBuilder("cmd.exe", "/c",
                        "JRE\\bin\\java.exe -jar updater.jar install").start();
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        // get db status
        if (!new File("database.db").exists()) {
            new Main.Database().mkdb();
        }

        // set application version
        try (PrintStream out = new PrintStream(
                new File("version.ini"))) {
            out.println(version);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
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
