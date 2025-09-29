package main;

import model.Book;
import service.BookService;
import service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        BookService bookService = new BookService();

        System.out.println("=== Library Management System ===");
        System.out.print("Username: "); String username = sc.nextLine();
        System.out.print("Password: "); String password = sc.nextLine();

        if (!userService.login(username, password)) {
            System.out.println("Invalid credentials! Exiting...");
            return;
        }
        System.out.println("Login successful!\n");

        while (true) {
            System.out.println("--- Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Search Book");
            System.out.println("6. View Books by Category");
            System.out.println("7. Reports");
            System.out.println("8. Exit");
            System.out.print("Choose: "); int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: "); String title = sc.nextLine();
                    System.out.print("Author: "); String author = sc.nextLine();
                    System.out.print("Category: "); String category = sc.nextLine();
                    System.out.print("Availability (true/false): "); boolean available = sc.nextBoolean();
                    bookService.addBook(new Book(title, author, category, available));
                }
                case 2 -> {
                    List<Book> books = bookService.getAllBooks();
                    books.sort(Comparator.comparing(Book::getTitle));
                    System.out.println("ID | Title | Author | Category | Availability");
                    books.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Book ID to update: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Title: "); String newTitle = sc.nextLine();
                    System.out.print("New Author: "); String newAuthor = sc.nextLine();
                    System.out.print("New Category: "); String newCategory = sc.nextLine();
                    System.out.print("Availability (true/false): "); boolean newAvailable = sc.nextBoolean();
                    bookService.updateBook(id, new Book(newTitle, newAuthor, newCategory, newAvailable));
                }
                case 4 -> {
                    System.out.print("Book ID to delete: "); int id = sc.nextInt();
                    bookService.deleteBook(id);
                }
                case 5 -> {
                    System.out.print("Keyword: "); String kw = sc.nextLine();
                    bookService.searchBooks(kw).forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Category: "); String cat = sc.nextLine();
                    bookService.getBooksByCategory(cat).forEach(System.out::println);
                }
                case 7 -> {
                    System.out.println("Total Books: " + bookService.getTotalBooksCount());
                    System.out.println("Available Books: " + bookService.getAvailableBooksCount());
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    sc.close(); return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
