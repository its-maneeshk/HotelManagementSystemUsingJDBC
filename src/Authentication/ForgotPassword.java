package Authentication;

import Database.ConnectingDB;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Scanner;

public class ForgotPassword {

    public void initiatePasswordReset() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username or email: ");
        String userEmailOrUsername = sc.nextLine();

        // Generate reset token
        String resetToken = generateResetToken();

        // Store reset token in the database with timestamp
        boolean isTokenSaved = saveResetToken(userEmailOrUsername, resetToken);

        if (isTokenSaved) {
            // Send token to user's email (mock)
            System.out.println("Password reset link has been sent to your email.");
            System.out.println("Reset Token (for demo purposes): " + resetToken);
        } else {
            System.out.println("Failed to initiate password reset. Please check your username or email.");
        }
    }

    private String generateResetToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    private boolean saveResetToken(String userEmailOrUsername, String resetToken) {
        String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem";
        String dbUsername = "root";
        String dbPassword = "password";

        String updateTokenQuery = "UPDATE users SET resetToken = ?, tokenExpiry = ? WHERE email = ? OR userName = ?";

        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(updateTokenQuery)) {

            pstmt.setString(1, resetToken);
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis() + 3600000)); // Token valid for 1 hour
            pstmt.setString(3, userEmailOrUsername);
            pstmt.setString(4, userEmailOrUsername);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
