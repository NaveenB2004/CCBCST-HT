package Main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NaveenB2004
 */
public class Database {

    public static Connection conn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }

    public void mkdb() {
        try {
            // table for login
            Connection conn0 = conn();
            Statement stmt0 = conn0.createStatement();
            stmt0.executeUpdate("CREATE TABLE login("
                    + "id INTEGER PRIMARY KEY,"
                    + "userName TEXT NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "lastLogin TEXT);");
            conn0.close();

            // table for scouts
            Connection conn1 = conn();
            Statement stmt1 = conn1.createStatement();
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
                    + "whatsapp TEXT);");
            conn1.close();

            //table for attendance marking
            Connection conn2 = conn();
            Statement stmt2 = conn2.createStatement();
            stmt2.executeUpdate("CREATE TABLE attendance("
                    + "id INTEGER PRIMARY KEY,"
                    + "scoutId INTEGER NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "status TEXT NOT NULL,"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id) "
                    + "ON DELETE CASCADE);");
            conn2.close();

            // table for tests
            Connection conn3 = conn();
            Statement stmt3 = conn3.createStatement();
            stmt3.executeUpdate("CREATE TABLE tests("
                    + "id INTEGER PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "defaultMark INTEGER NOT NULL);");
            conn3.close();

            // table for test marks
            Connection conn4 = conn();
            Statement stmt4 = conn4.createStatement();
            stmt4.executeUpdate("CREATE TABLE testMarks("
                    + "id INTEGER PRIMARY KEY,"
                    + "scoutId INTEGER NOT NULL,"
                    + "testId INTEGER NOT NULL,"
                    + "marks INTEGER NOT NULL,"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id) "
                    + "ON DELETE CASCADE,"
                    + "FOREIGN KEY (testId) REFERENCES tests(id) "
                    + "ON DELETE CASCADE);");
            conn4.close();

            // table for activities
            Connection conn5 = conn();
            Statement stmt5 = conn5.createStatement();
            stmt5.executeUpdate("CREATE TABLE activities("
                    + "id INTEGER PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "defaultMark INTEGER NOT NULL);");
            conn5.close();

            // table for activity marks
            Connection conn6 = conn();
            Statement stmt6 = conn6.createStatement();
            stmt6.executeUpdate("CREATE TABLE activityMarks("
                    + "id INTEGER PRIMARY KEY,"
                    + "scoutId INTEGER NOT NULL,"
                    + "activityId INTEGER NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "marks INTEGER NOT NULL,"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id)"
                    + " ON DELETE CASCADE,"
                    + "FOREIGN KEY (activityId) REFERENCES activities(id) "
                    + "ON DELETE CASCADE);");
            conn6.close();

            // add user login credentials
            Connection conn7 = conn();
            Statement stmt7 = conn7.createStatement();
            stmt7.executeUpdate("INSERT INTO login "
                    + "(id, username, password, lastLogin) VALUES "
                    + "(1, 'admin', 'admin', 'First Login!')");
            conn7.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
