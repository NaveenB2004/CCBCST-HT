package Main;

import java.io.IOException;

/**
 *
 * @author NaveenB2004
 */
public class Updates {

    public void updateCheck() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new ProcessBuilder("cmd.exe", "/c",
                        "JRE\\bin\\java.exe -jar updater.jar").start();
                } catch (IOException ex) {
                    System.out.println(ex);}
                        }
        }).start();
    }
}
