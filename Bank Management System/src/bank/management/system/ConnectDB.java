package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {
    private static final Logger logger = Logger.getLogger(ConnectDB.class.getName());

    Connection connection;
    Statement statement;

    public ConnectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sj_bank_db", "root", "kingOfWater");
            statement = connection.createStatement();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Database Connection Error", e);
        }
    }

    public static void main(String[] args) {
        new ConnectDB();
    }
}
