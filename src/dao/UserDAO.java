package dao;

import model.User;
import util.DBConnection;

import java.sql.*;

public class UserDAO {
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return false;
    }
}
