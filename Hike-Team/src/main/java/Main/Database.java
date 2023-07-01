package Main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NaveenB2004
 */
public class Database {

    public static Connection conn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = conn.createStatement();
            stmt.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }

    public void mkdb() {
        try {
            // table for scouts
            Statement stmt1 = conn().createStatement();
            stmt1.executeUpdate("CREATE TABLE scouts("
                    + "id INTEGER PRIMARY KEY,"
                    + "schoolIndex TEXT,"
                    + "callName TEXT NOT NULL,"
                    + "nameWithInitials TEXT NOT NULL,"
                    + "fullName TEXT NOT NULL,"
                    + "class TEXT NOT NULL,"
                    + "birthDate TEXT NOT NULL,"
                    + "address TEXT NOT NULL,"
                    + "guardianName TEXT NOT NULL,"
                    + "guardianContact TEXT NOT NULL,"
                    + "whatsapp TEXT)");

            //table for attendance marking
            Statement stmt2 = conn().createStatement();
            stmt2.executeUpdate("CREATE TABLE attendance("
                    + "id INTEGER PRIMARY KEY,"
                    + "scoutId INTEGER NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "status TEXT NOT NULL,"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id) "
                    + "ON DELETE CASCADE)");

            // table for tests
            Statement stmt3 = conn().createStatement();
            stmt3.executeUpdate("CREATE TABLE tests("
                    + "id INTEGER PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "defaultMark INTEGER NOT NULL)");

            // table for test marks
            Statement stmt4 = conn().createStatement();
            stmt4.executeUpdate("CREATE TABLE testMarks("
                    + "id INTEGER PRIMARY KEY,"
                    + "scoutId INTEGER NOT NULL,"
                    + "testId INTEGER NOT NULL,"
                    + "marks INTEGER NOT NULL,"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id) "
                    + "ON DELETE CASCADE,"
                    + "FOREIGN KEY (testId) REFERENCES tests(id) "
                    + "ON DELETE CASCADE)");

            // table for activities
            Statement stmt5 = conn().createStatement();
            stmt5.executeUpdate("CREATE TABLE activities("
                    + "id INTEGER PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "defaultMark INTEGER NOT NULL)");

            // table for activity marks
            Statement stmt6 = conn().createStatement();
            stmt6.executeUpdate("CREATE TABLE activityMarks("
                    + "id INTEGER PRIMARY KEY,"
                    + "scoutId INTEGER NOT NULL,"
                    + "activityId INTEGER NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "marks INTEGER NOT NULL,"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id) "
                    + "ON DELETE CASCADE,"
                    + "FOREIGN KEY (activityId) REFERENCES activities(id) "
                    + "ON DELETE CASCADE)");
            conn().close();
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
