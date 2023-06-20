package process;

import java.awt.Frame;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
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
                int x = 0;
                while ((line = br.readLine()) != null) {
                    if (x == 0) {
                        tempVer = line;
                    }
                    x++;
                }
            }
        } catch (IOException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }

        String thisVer = null;
        try (Stream<String> lines = Files.lines(
                Paths.get("version.ini"))) {
            thisVer = lines.skip(0).findFirst().get();
        } catch (Exception e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            if (Float.parseFloat(thisVer) != Float.parseFloat(tempVer)) {
                downloadUpdate(tempVer, thisVer);
            } else {
                JOptionPane.showMessageDialog(new Frame(), "This is the latest version!");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void downloadUpdate(String tempVer, String thisVer) {
        new prograss().setVisible(true);
        prograss.jLabel4.setText("Your Version : " + thisVer + " - New Version : " + tempVer);
        String newURL = null;
        URL urlx = null;
        try {
            URL url = new URL("https://drive.google.com/uc?id=1itN3gxFaCv0IXKPQ2Cp_sniGBEny0lEs");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                int x = 0;
                while ((line = br.readLine()) != null) {
                    if (x != 0) {
                        newURL = line;
                    }
                    x++;
                }
            }
            urlx = new URL(newURL);
        } catch (IOException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            FileUtils.copyURLToFile(urlx, new File("CCBCST Hike-Team.temp"));
        } catch (IOException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }
        try (PrintStream out = new PrintStream(
                new File("version.ini"))) {
            out.println(tempVer);
        } catch (FileNotFoundException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }
        new File("CCBCST Hike-Team.temp").renameTo(new File("CCBCST Hike-Team.nnb"));

        prograss.disposeText.setText("0");
    }

    public void installer() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }
        new File("CCBCST Hike-Team.jar").delete();
        new File("CCBCST Hike-Team.nnb").renameTo(new File("CCBCST Hike-Team.jar"));
        try {
            new ProcessBuilder("cmd.exe", "/c",
                    "JRE\\bin\\java.exe -jar \"CCBCST Hike-Team.jar\"").start();
            System.exit(0);
        } catch (IOException e) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
