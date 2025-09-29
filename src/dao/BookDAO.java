package dao;

import model.Book;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, category, availability) VALUES(?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setBoolean(4, book.isAvailability());
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("availability")
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return books;
    }

    public void updateBook(int id, Book book) {
        String sql = "UPDATE books SET title=?, author=?, category=?, availability=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setBoolean(4, book.isAvailability());
            stmt.setInt(5, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Book updated!" : "Book not found.");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Book deleted!" : "Book not found.");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("availability")
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return books;
    }
}
