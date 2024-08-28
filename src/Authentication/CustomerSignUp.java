package Authentication;

import Database.ConnectingDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CustomerSignUp {

    public void customerCreateAccount() {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                **********************************************************************************
                *                                                                                *
                *                    >>>>>> Welcome To Sign Up Page <<<<<<                       *
                *                                                                                *
                **********************************************************************************
                """);

        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter a Username: ");
        String newUsername = sc.nextLine();

        System.out.print("Enter an Email: ");
        String email = sc.nextLine();

        System.out.print("Enter a Password: ");
        String newPassword = sc.nextLine();

        // Encrypt the password using SHA-256
        String encryptedPassword = encryptPassword(newPassword);

        // Store user information in the database
        boolean isAccountCreated = saveNewUserToDatabase(firstName, lastName, newUsername, email, encryptedPassword);

        if (isAccountCreated) {
            System.out.println("\u001B[92m" + "Account created successfully! You can now log in." + "\033[0m");
        } else {
            System.out.println("\u001B[91m" + "Failed to create account. Username or Email may already be taken." + "\033[0m");
        }
    }

    private boolean saveNewUserToDatabase(String fName, String lName, String userName, String email, String password) {
        String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem";
        String dbUsername = "root";
        String dbPassword = "password";

        String insertUserQuery = "INSERT INTO users (fName, lName, userName, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(insertUserQuery)) {

            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, userName);
            pstmt.setString(4, email);
            pstmt.setString(5, password);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
