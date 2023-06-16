package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author NaveenB2004
 */
public class VerChanges {

    public void callChanges() {
        v04();
    }

    private void v04() {
        // 0.3 to 0.4
        // remove login from db
        Connection conn = Database.conn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS login");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
