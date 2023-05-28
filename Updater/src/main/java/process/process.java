package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author NaveenB2004
 */
public class process {

    public void checkUpdates() {
        // version link 1st row :
        // package link 2nd row :
        // https://drive.google.com/uc?id=1itN3gxFaCv0IXKPQ2Cp_sniGBEny0lEs
        String tempVer = null;
        try {
            URL url = new URL("https://drive.google.com/uc?id=1itN3gxFaCv0IXKPQ2Cp_sniGBEny0lEs");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    tempVer = line;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        String thisVer = null;
        try (Stream<String> lines = Files.lines(
                Paths.get("version.ini"))) {
            thisVer = lines.skip(0).findFirst().get();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            if (Float.parseFloat(thisVer) < Float.parseFloat(tempVer)) {
                downloadUpdate(tempVer);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }

    private void downloadUpdate(String tempVer) {
        new prograss().setVisible(true);
        String newURL = null;
        URL urlx = null;
        try {
            URL url = new URL("https://drive.google.com/uc?id=1itN3gxFaCv0IXKPQ2Cp_sniGBEny0lEs");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int x = 0;
                    if (x != 0) {
                        newURL = line;
                    }
                    x++;
                }
            }
            urlx = new URL(newURL);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            FileUtils.copyURLToFile(urlx, new File("CCBCST Hike-Team.nnb"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try (PrintStream out = new PrintStream(
                new File("version.ini"))) {
            out.println(tempVer);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void installer() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        new File("CCBCST Hike-Team.jar").delete();
        new File("CCBCST Hike-Team.nnb").renameTo(new File("CCBCST Hike-Team.jar"));
        try {
            new ProcessBuilder("cmd.exe", "/c",
                    "JRE\\bin\\java.exe -jar \"CCBCST Hike-Team.jar\"").start();
            System.exit(0);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
