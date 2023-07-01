package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NaveenB2004
 */
public class VerChanges {

    public void callChanges() {
        v04();
        v06();
    }

    private void v04() {
        // 0.3 to 0.4
        // remove login from db
        Connection conn = Database.conn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS login");
        } catch (SQLException ex) {
            Logger.getLogger(VerChanges.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void v06() {
        // 0.5 to 0.6
        // reset fields that currept in v0.1
        Connection conn = Database.conn();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, whatsapp "
                    + "FROM scouts");
            while (rs.next()) {
                if (rs.getString(2).length() != 10) {
                    Statement stmt0 = conn.createStatement();
                    stmt0.executeUpdate("UPDATE scouts "
                            + "SET whatsapp='0000000000' WHERE "
                            + "id='" + rs.getString(1) + "'");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VerChanges.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
