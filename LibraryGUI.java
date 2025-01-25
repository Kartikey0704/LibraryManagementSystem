package Project_AdvLMS;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame {
    private Library library;
    private JTextField titleField, authorField, isbnField;
    private JTextArea outputArea;

    public LibraryGUI() {
        library = new Library();

        // Setting up the frame
        setTitle("Library Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for adding books
        JPanel addPanel = new JPanel(new GridLayout(4, 2));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Book"));
        
        addPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        addPanel.add(titleField);

        addPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        addPanel.add(authorField);

        addPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        addPanel.add(isbnField);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBook());
        addPanel.add(addButton);

        // Text area for displaying output
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Panel for borrowing and returning books
        JPanel borrowReturnPanel = new JPanel(new GridLayout(1, 2));
        
        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(e -> borrowBook());
        borrowReturnPanel.add(borrowButton);
        
        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(e -> returnBook());
        borrowReturnPanel.add(returnButton);

        // Adding components to the frame
        add(addPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(borrowReturnPanel, BorderLayout.SOUTH);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String ISBN = isbnField.getText();

        if (!title.isEmpty() && !author.isEmpty() && !ISBN.isEmpty()) {
            Book book = new Book(title, author, ISBN);
            library.addBook(book);
            outputArea.append("Book added: " + title + "\n");
        } else {
            outputArea.append("Please fill in all fields to add a book.\n");
        }
    }

    private void borrowBook() {
        String ISBN = isbnField.getText();
        if (!ISBN.isEmpty()) {
            String result = library.borrowBook(ISBN);
            outputArea.append(result + "\n");
        } else {
            outputArea.append("Please enter ISBN to borrow a book.\n");
        }
    }

    private void returnBook() {
        String ISBN = isbnField.getText();
        if (!ISBN.isEmpty()) {
            String result = library.returnBook(ISBN);
            outputArea.append(result + "\n");
        } else {
            outputArea.append("Please enter ISBN to return a book.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.setVisible(true);
        });
    }
}
