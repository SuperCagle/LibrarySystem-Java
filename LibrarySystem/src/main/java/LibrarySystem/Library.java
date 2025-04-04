package LibrarySystem;

import java.time.LocalDateTime;
import java.util.*;

public class Library {
    private Map<String, Book> bookCollection = new HashMap<>();
    private Map<String, User> userCollection = new HashMap<>();
    private List<Book> overdueBooks = new ArrayList<>();
    private List<Book> popBooks = new ArrayList<>();

    public Library() {
        
        bookCollection.putAll(JSONHandler.loadBooksFromJson("books.json"));
        userCollection.putAll(JSONHandler.loadUsersFromJson("users.json"));


        if(bookCollection.isEmpty()) {
            addBook(new Book("12345", "Effective Java", "Joshua Bloch", 2008));
            addBook(new Book("67890", "Clean Code", "Robert C. Martin", 2008));
            addBook(new Book("11223", "The Pragmatic Programmer", "Andrew Hunt & David Thomas", 1999));
        }

    }

    public Map<String, User> getUserCollection() {
        return userCollection;
    }

    public Collection<Book> getBooks() {
        return bookCollection.values();
    }

    // Add & Remove Methods
    public void addUser(String name,String username, String password) {
        User newUser = new User(name,username,password);  
        addUser(newUser); 
    }

    public void addUser(User user) {
        userCollection.put(user.getId(), user); 
        JSONHandler.saveUsersToJson(userCollection, "users.json"); 
    }

    public void addBook(Book book) {
        bookCollection.put(book.getIsbn(), book); 
        JSONHandler.saveBooksToJson(bookCollection, "books.json"); 
    }

    public void removeBook(Book book) {
        bookCollection.remove(book.getIsbn());
        JSONHandler.saveBooksToJson(bookCollection, "books.json");
    }

    public void removeUser(User user) {
        userCollection.remove(user.getId());
        JSONHandler.saveUsersToJson(userCollection, "users.json");
    }
    public Book getBookByIsbn(String isbn) {
        return bookCollection.get(isbn);
    }

    // Borrow & Return Books
    public void borrowBook(String userId, String isbn) {

        if (bookCollection.containsKey(isbn) && userCollection.containsKey(userId)) {
            Book book = bookCollection.get(isbn);
            User user = userCollection.get(userId);

            if (book.isAvailable()) {
                book.setAvailable(false);
                user.addUserBook(isbn);
                book.setBorrowDate(LocalDateTime.now());
                book.setDueDate(LocalDateTime.now().plusMinutes(2)); // Adjust due date as needed

                book.borrowedCounter();

                // Save updated data to JSON
                JSONHandler.saveUsersToJson(userCollection, "users.json");
                JSONHandler.saveBooksToJson(bookCollection, "books.json");

                System.out.println(user.getName() + " has checked out: " + book.getTitle());
                System.out.println("Due date: " + book.getDueDate());
            } else {
                System.out.println("Book is currently unavailable. Please try another book!");
            }
        } else {
            System.out.println("User or Book does not exist. Please try again!");
        }
    }

    public void returnBook(String userId, String isbn) {
        if (bookCollection.containsKey(isbn) && userCollection.containsKey(userId)) {
            Book book = bookCollection.get(isbn);
            User user = userCollection.get(userId);

            if (user.getBookList().contains(isbn)) {
                book.setAvailable(true);
                user.removeUserBook(isbn);
                System.out.println(user.getName() + " has returned " + book.getTitle());

                // Save updated data to JSON
                JSONHandler.saveUsersToJson(userCollection, "users.json");
                JSONHandler.saveBooksToJson(bookCollection, "books.json");
            } else {
                System.out.println("User doesn't have this book to return. Please try again!");
            }
        } else {
            System.out.println("User or Book doesn't exist. Please try again!");
        }
    }

    // Overdue book checks
    public void overdueCheck() {
        for (Book b : bookCollection.values()) {
            if (b.getDueDate() != null && b.getDueDate().isBefore(LocalDateTime.now())) {
                overdueBooks.add(b);
            }
        }
    }

    public void overduePrint() {
        if (!overdueBooks.isEmpty()) {
            System.out.println("The following books are overdue: ");
            for (Book b : overdueBooks) {
                System.out.println(b.getTitle() + " (Due: " + b.getDueDate() + ")");
            }
        } else {
            System.out.println("No overdue books.");
        }
    }

    // Print books in the library
    public void printBooks() {
        System.out.println("Library book collection: ");
        for (Book b : bookCollection.values()) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
    }

    // Popular books sorted by borrow count
    public void popularBooks() {
        popBooks.clear();
        popBooks.addAll(bookCollection.values());

        popBooks.sort(Comparator.comparingInt(Book::getNumBorrowed).reversed());

        System.out.println("Most popular books: ");
        for (int i = 0; i < Math.min(3, popBooks.size()); i++) {
            System.out.println((i + 1) + ". " + popBooks.get(i).getTitle());
        }
    }
}
