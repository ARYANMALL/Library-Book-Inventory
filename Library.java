import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private FileHandler fileHandler;

    public Library() {
        this.books = new ArrayList<>();
        this.fileHandler = new FileHandler();
        loadBooksFromFile();
    }

    public void addBook(String id, String title, String author) {
        // Check if book ID already exists
        for (Book book : books) {
            if (book.getId().equals(id)) {
                System.out.println("Error: Book with ID " + id + " already exists!");
                return;
            }
        }
        
        Book newBook = new Book(id, title, author);
        books.add(newBook);
        saveBooksToFile();
        System.out.println("Book added successfully: " + title);
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\n=== ALL BOOKS IN LIBRARY ===");
        System.out.println("----------------------------------------------------------------");
        for (Book book : books) {
            System.out.println(book.toDisplayString());
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("Total books: " + books.size());
    }

    public void searchBooks(String keyword) {
        List<Book> results = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword) ||
                book.getAuthor().toLowerCase().contains(keyword) ||
                book.getId().toLowerCase().contains(keyword)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found matching: " + keyword);
        } else {
            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("----------------------------------------------------------------");
            for (Book book : results) {
                System.out.println(book.toDisplayString());
            }
            System.out.println("----------------------------------------------------------------");
            System.out.println("Found " + results.size() + " book(s)");
        }
    }

    public void issueBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    saveBooksToFile();
                    System.out.println("Book issued successfully: " + book.getTitle());
                } else {
                    System.out.println("Book is already issued: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("Book not found with ID: " + bookId);
    }

    public void returnBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    saveBooksToFile();
                    System.out.println("Book returned successfully: " + book.getTitle());
                } else {
                    System.out.println("Book is already available: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("Book not found with ID: " + bookId);
    }

    public void displayAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        if (availableBooks.isEmpty()) {
            System.out.println("No available books at the moment.");
            return;
        }

        System.out.println("\n=== AVAILABLE BOOKS ===");
        System.out.println("----------------------------------------------------------------");
        for (Book book : availableBooks) {
            System.out.println(book.toDisplayString());
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("Available books: " + availableBooks.size());
    }

    private void loadBooksFromFile() {
        List<String> bookData = fileHandler.readFromFile();
        for (String data : bookData) {
            Book book = parseBookData(data);
            if (book != null) {
                books.add(book);
            }
        }
    }

    private void saveBooksToFile() {
        List<String> bookData = new ArrayList<>();
        for (Book book : books) {
            bookData.add(book.toString());
        }
        fileHandler.writeToFile(bookData);
    }

    private Book parseBookData(String data) {
        try {
            String[] parts = data.split(",");
            if (parts.length == 4) {
                String id = parts[0];
                String title = parts[1];
                String author = parts[2];
                boolean isAvailable = Boolean.parseBoolean(parts[3]);
                return new Book(id, title, author, isAvailable);
            }
        } catch (Exception e) {
            System.out.println("Error parsing book data: " + data);
        }
        return null;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}