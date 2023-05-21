package Main;

import java.sql.Statement;
import java.io.File;
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
                    + "id int(2) NOT NULL AUTO_INCREMENT,"
                    + "userName varchar(20) NOT NULL,"
                    + "password varchar(20) NOT NULL,"
                    + "lastLogin varchar(20),"
                    + "PRIMARY KEY (id));");
            
            // table for scouts
            Statement stmt1 = conn().createStatement();
            stmt1.executeUpdate("CREATE TABLE scouts("
                    + "id int(12) NOT NULL AUTO_INCREMENT,"
                    + "callName varcha(20) NOT NULL,"
                    + "nameWithInitials varchar(50) NOT NULL,"
                    + "fullName varchar(80) NOT NULL,"
                    + "birthDate date NOT NULL,"
                    + "address varchar(255) NOT NULL,"
                    + "guardianName varchar(50) NOT NULL,"
                    + "guardianContact varchar(9) NOT NULL,"
                    + "PRIMARY KEY (id));");
            
            //table for attendance marking
            Statement stmt2 = conn().createStatement();
            stmt2.executeUpdate("CREATE TABLE attendance("
                    + "id int(12) NOT NULL AUTO_INCREMENT,"
                    + "scoutId int(12) NOT NULL,"
                    + "date date NOT NULL,"
                    + "status int(1) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + "FOREIGN KEY (scoutId) RFERENCES scouts(id));");
            
            // table for 
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
