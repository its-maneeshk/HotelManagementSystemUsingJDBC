package Authentication;

import Database.ConnectingDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class ResetPassword {

    public void resetPassword() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your reset token: ");
        String resetToken = sc.nextLine();

        if (isTokenValid(resetToken)) {
            System.out.print("Enter your new password: ");
            String newPassword = sc.nextLine();

            // Encrypt the new password
            String encryptedPassword = encryptPassword(newPassword);

            // Update the password in the database
            if (updatePassword(resetToken, encryptedPassword)) {
                System.out.println("Your password has been reset successfully.");
            } else {
                System.out.println("Failed to reset the password.");
            }
        } else {
            System.out.println("Invalid or expired reset token.");
        }
    }

    private boolean isTokenValid(String resetToken) {
        String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem";
        String dbUsername = "root";
        String dbPassword = "password";

        String verifyTokenQuery = "SELECT tokenExpiry FROM users WHERE resetToken = ?";

        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(verifyTokenQuery)) {

            pstmt.setString(1, resetToken);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Timestamp tokenExpiry = rs.getTimestamp("tokenExpiry");
                return tokenExpiry != null && tokenExpiry.after(new Timestamp(System.currentTimeMillis()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private boolean updatePassword(String resetToken, String newPassword) {
        String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem";
        String dbUsername = "root";
        String dbPassword = "password";

        String updatePasswordQuery = "UPDATE users SET password = ?, resetToken = NULL, tokenExpiry = NULL WHERE resetToken = ?";

        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(updatePasswordQuery)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, resetToken);

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
