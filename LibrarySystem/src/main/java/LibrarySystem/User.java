package LibrarySystem;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String name;
    private String id = UUID.randomUUID().toString();
    private List<String> borrowedBooks = new ArrayList<String>();
    private String password;
    private String username;

    public User(){

    }

    public User(String name, String username, String password){
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    // Getters 
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public List<String> getBookList(){
        return borrowedBooks;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername(){
        return username;
    }

    // Add & Remove
    public void addUserBook(String i){
        borrowedBooks.add(i);
    }
    public void removeUserBook(String i){
        borrowedBooks.remove(i);
    }
}
