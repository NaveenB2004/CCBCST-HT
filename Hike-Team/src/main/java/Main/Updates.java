package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author NaveenB2004
 */
public class Updates {

    public static String version = "0.3";

    public void update() {
        if (new File("CCBCST Hike-Team.nnb").exists()) {
            try {
                new ProcessBuilder("cmd.exe", "/c",
                        "JRE\\bin\\java.exe -jar updater.jar install").start();
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            // set application version
            try (PrintStream out = new PrintStream(
                    new File("version.ini"))) {
                out.println(Main.Updates.version);
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            
            try {
                new ProcessBuilder("cmd.exe", "/c",
                        "JRE\\bin\\java.exe -jar updater.jar noInstall").start();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
