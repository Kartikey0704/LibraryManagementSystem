package Project_AdvLMS;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> books = new HashMap<>();

    // Method to add a book
    public void addBook(Book book) {
        books.put(book.getISBN(), book);
    }

    // Method to search for a book by ISBN
    public Book searchBookByISBN(String ISBN) {
        return books.get(ISBN);
    }

    // Method to borrow a book
    public String borrowBook(String ISBN) {
        Book book = books.get(ISBN);
        if (book != null && !book.isBorrowed()) {
            book.borrow();
            return "Borrowed: " + book.getTitle() + ". Due date: " + book.getDueDate();
        } else {
            return "Book is either unavailable or already borrowed.";
        }
    }

    // Method to return a book
    public String returnBook(String ISBN) {
        Book book = books.get(ISBN);
        if (book != null && book.isBorrowed()) {
            LocalDate now = LocalDate.now();
            long daysLate = now.isAfter(book.getDueDate()) ? now.toEpochDay() - book.getDueDate().toEpochDay() : 0;
            book.returnBook();
            return daysLate > 0 ? "Late by " + daysLate + " days. Please return earlier next time!" :
                    "Book returned successfully.";
        } else {
            return "Book is not borrowed or does not exist.";
        }
    }
}

