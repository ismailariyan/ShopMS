package shopms;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    static Connection conn = null;

    public static Connection connectDB() {
        if (conn != null) return conn;
        String dbName = "shop";
        String Username = "root";
        String password = "@ariyan77";
        return connectDB(dbName, Username, password);

    }

    private static Connection connectDB(String databaseName, String UserName, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?user=" + UserName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}