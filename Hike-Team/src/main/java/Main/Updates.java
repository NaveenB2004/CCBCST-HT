package Main;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author NaveenB2004
 */
public class Updates {

    public static String version = "0.3";

    public void updateCheck() {
        try {
            new ProcessBuilder("cmd.exe", "/c",
                    "JRE\\bin\\java.exe -jar updater.jar noInstall").start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void updateInstall() {
        if (new File("CCBCST Hike-Team.nnb").exists()) {
            try {
                new ProcessBuilder("cmd.exe", "/c",
                        "JRE\\bin\\java.exe -jar updater.jar install").start();
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
