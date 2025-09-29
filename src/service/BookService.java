package service;

import dao.BookDAO;
import model.Book;

import java.util.List;

public class BookService {
    private BookDAO dao = new BookDAO();

    public void addBook(Book book) { dao.addBook(book); }
    public List<Book> getAllBooks() { return dao.getAllBooks(); }
    public void updateBook(int id, Book book) { dao.updateBook(id, book); }
    public void deleteBook(int id) { dao.deleteBook(id); }
    public List<Book> searchBooks(String keyword) { return dao.searchBooks(keyword); }
    public List<Book> getBooksByCategory(String category) {
        return dao.getAllBooks().stream().filter(b -> b.getCategory().equalsIgnoreCase(category)).toList();
    }
    public long getAvailableBooksCount() { return dao.getAllBooks().stream().filter(Book::isAvailability).count(); }
    public long getTotalBooksCount() { return dao.getAllBooks().size(); }
}
