package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectingDB {
    public List<User> databaseConnection() {
        String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem";
        String username = "root";
        String password = "password";
        String query = "SELECT * FROM users";

        List<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver loaded successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
//            System.out.println("Statement executed");
            ResultSet rs = stmt.executeQuery(query);
//            System.out.println("Executing query");

            while (rs.next()) {
                String uName = rs.getString("userName");
                String uPassword = rs.getString("password");
                User user = new User(uName, uPassword);
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
}

