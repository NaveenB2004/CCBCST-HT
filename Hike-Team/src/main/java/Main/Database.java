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
            conn = DriverManager.getConnection("jdbc:sqlite:CCBCST.db");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }

    public void mkdb() {
        try {
            // table for login
            Statement stmt0 = conn().createStatement();
            stmt0.executeUpdate("CREATE TABLE login("
                    + "id int(2) NOT NULL,"
                    + "userName varchar(20) NOT NULL,"
                    + "password varchar(20) NOT NULL,"
                    + "lastLogin varchar(20),"
                    + "PRIMARY KEY (id));");

            // table for scouts
            Statement stmt1 = conn().createStatement();
            stmt1.executeUpdate("CREATE TABLE scouts("
                    + "id int(12) NOT NULL,"
                    + "schoolIndex int(12),"
                    + "callName varcha(20) NOT NULL,"
                    + "nameWithInitials varchar(50) NOT NULL,"
                    + "fullName varchar(80) NOT NULL,"
                    + "class varchar(4) NOT NULL,"
                    + "birthDate date NOT NULL,"
                    + "address varchar(255) NOT NULL,"
                    + "guardianName varchar(50) NOT NULL,"
                    + "guardianContact varchar(9) NOT NULL,"
                    + "whatsapp varchar(30),"
                    + "PRIMARY KEY (id));");

            //table for attendance marking
            Statement stmt2 = conn().createStatement();
            stmt2.executeUpdate("CREATE TABLE attendance("
                    + "id int(12) NOT NULL,"
                    + "scoutId int(12) NOT NULL,"
                    + "date date NOT NULL,"
                    + "status int(1) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id));");

            // table for tests
            Statement stmt3 = conn().createStatement();
            stmt3.executeUpdate("CREATE TABLE tests("
                    + "id int(12) NOT NULL,"
                    + "name varchar(50) NOT NULL,"
                    + "date date NOT NULL,"
                    + "defaultMark int(4) NOT NULL,"
                    + "PRIMARY KEY (id));");

            // table for test marks
            Statement stmt4 = conn().createStatement();
            stmt4.executeUpdate("CREATE TABLE testMarks("
                    + "id int(12) NOT NULL,"
                    + "scoutId int(12) NOT NULL,"
                    + "testId int(12) NOT NULL,"
                    + "marks int(4) NOT NULL,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id),"
                    + "FOREIGN KEY (testId) REFERENCES tests(id));");

            // table for activities
            Statement stmt5 = conn().createStatement();
            stmt5.executeUpdate("CREATE TABLE activities("
                    + "id int(12) NOT NULL,"
                    + "name varchar(30) NOT NULL,"
                    + "defaultMark int(4) NOT NULL,"
                    + "PRIMARY KEY (id));");

            // table for activity marks
            Statement stmt6 = conn().createStatement();
            stmt6.executeUpdate("CREATE TABLE activityMarks("
                    + "id int(12) NOT NULL,"
                    + "scoutId int(12) NOT NULL,"
                    + "activityId int(12) NOT NULL,"
                    + "date date NOT NULL,"
                    + "marks int(4) NOT NULL,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (scoutId) REFERENCES scouts(id),"
                    + "FOREIGN KEY (activityId) REFERENCES activities(id));");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
