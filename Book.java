package Project_AdvLMS;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isBorrowed;
    private LocalDate dueDate;  // Due date for borrowed books
    private static final int BORROW_PERIOD_DAYS = 14;

    // Constructor
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false;
        this.dueDate = null; // Not borrowed by default
    }

    // Getters and setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public boolean isBorrowed() { return isBorrowed; }
    public LocalDate getDueDate() { return dueDate; }
    
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void borrow() {
        isBorrowed = true;
        dueDate = LocalDate.now().plusDays(BORROW_PERIOD_DAYS);
    }

    public void returnBook() {
        isBorrowed = false;
        dueDate = null;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", ISBN='" + ISBN + '\'' +
                ", dueDate=" + (dueDate != null ? dueDate : "N/A") + '}';
    }
}
