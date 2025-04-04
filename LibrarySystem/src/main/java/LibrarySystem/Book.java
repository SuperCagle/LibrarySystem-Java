package LibrarySystem;
import java.time.LocalDateTime;

public class Book{
    private String title;
    private String author;
    private String isbn;
    private int numPages;
    private boolean isAvailable;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private int numBorrowed;

    public Book() {}

    // Constructor
    public Book(String title, String author, String isbn, int numPages){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.numPages = numPages;
        this.isAvailable = true;
    }

    // Setters 
    public void setAvailable(boolean available){
        isAvailable = available;
    }
    public void setBorrowDate(LocalDateTime borrowDate){
        this.borrowDate = borrowDate;
    }
    public void setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
    }

    // Getters 
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getIsbn(){
        return isbn;
    }
    public int getNumPages(){
        return numPages;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public LocalDateTime getBorrowDate(){
        return borrowDate;
    }
    public LocalDateTime getDueDate(){
        return dueDate;
    }

    public int getNumBorrowed(){
        return numBorrowed;
    }

    // Counter
    public void borrowedCounter(){
        numBorrowed++;
    }


    // Override String for print
    public String toString(){
        return "- " + title + " by " + author + "\n";
    }

}