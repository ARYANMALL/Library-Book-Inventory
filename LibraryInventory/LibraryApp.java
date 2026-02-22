package LibraryInventory;
import java.util.Scanner;

public class LibraryApp {
    private Library library;
    private Scanner scanner;

    public LibraryApp() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== WELCOME TO LIBRARY BOOK INVENTORY SYSTEM ===");
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> addBook();
                case 2 -> library.displayAllBooks();
                case 3 -> searchBook();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> library.displayAvailableBooks();
                case 7 -> {
                    System.out.println("Thank you for using Library Inventory System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println(); // Empty line for better readability
        }
    }

    private void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add New Book");
        System.out.println("2. Display All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Display Available Books");
        System.out.println("7. Exit");
    }

    private void addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        
        library.addBook(id, title, author);
    }

    private void searchBook() {
        System.out.println("\n=== SEARCH BOOK ===");
        System.out.print("Enter search keyword (title, author, or ID): ");
        String keyword = scanner.nextLine();
        library.searchBooks(keyword);
    }

    private void issueBook() {
        System.out.println("\n=== ISSUE BOOK ===");
        System.out.print("Enter Book ID to issue: ");
        String bookId = scanner.nextLine();
        library.issueBook(bookId);
    }

    private void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();
        library.returnBook(bookId);
    }

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.start();
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}