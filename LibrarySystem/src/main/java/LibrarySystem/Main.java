package LibrarySystem;
import java.util.*;

public class Main {
    public static void main(String[] args) {
      
        Library library = new Library();

        String name = "";
        String username = "";
        String password = "";
        User newUser = null;

        boolean run = true;
        boolean intro = true;
        Scanner scanner = new Scanner(System.in);

       while(intro){
            System.out.println("Welcome to the Library! Are you a returning user? (Yes/No)");
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("no")){
                System.out.print("Please enter your name: ");
                name = scanner.nextLine();
                System.out.println("Now please enter a username click enter and then a password: ");
                username = scanner.nextLine();
                password = scanner.nextLine();

                newUser = new User(name,username,password);
                library.addUser(newUser);
                System.out.println("Welcome " + name + "!");
                System.out.println("Your unique ID is: " + newUser.getId());
                break;


            }
            else if(choice.equalsIgnoreCase("yes")){
               while(true){
                System.out.print("Please enter your username: ");
                String checkUsername = scanner.nextLine();
                System.out.print("Please enter your password: ");
                String checkPassword = scanner.nextLine();

                for(User user : library.getUserCollection().values()){
                    if(user.getUsername().equals(checkUsername) && user.getPassword().equals(checkPassword)){
                        newUser = user;
                        break;
                    }
                }
                if(newUser != null){
                    System.out.println("Welcome back " + newUser.getName() + "!");
                    intro = false;
                    break;
                }
                else{
                    System.out.println("Invalid username or password please try again!");
                }
               }
            }
            else{
                System.out.println("Invalid input, please try again!");
            }
       }


        while(run){

            System.out.print("Would you like to checkout, return a book or quit? - ");
            String answer = scanner.nextLine();

            if(answer.equalsIgnoreCase("checkout")){
                System.out.println("These are the books in the library");
                System.out.println("------------------------------------------------------");
                for(Book b : library.getBooks()){
                    System.out.println(b);
                }
                System.out.println("------------------------------------------------------");
                System.out.print("Please select the title of a book to checkout: ");
                String outBook = scanner.nextLine();
                for(Book b : library.getBooks()){
                    if(b.getTitle().equalsIgnoreCase(outBook)){
                       library.borrowBook(newUser.getId(), b.getIsbn());
                    }

                }


            }
            else if(answer.equalsIgnoreCase("return")){
                if(!newUser.getBookList().isEmpty()){
                    System.out.println("Here is the list of book(s) currently checked out to you: ");
                    System.out.println("------------------------------------------------------" + "\n");
                    for(String isbn : newUser.getBookList()){
                        Book b = library.getBookByIsbn(isbn);
                        if(b != null){
                            System.out.println(b);
                        }
                    }
                    System.out.println("------------------------------------------------------");
                    System.out.print("Pleas select the title of the book you'd like to return: ");
                    String returnBook = scanner.nextLine();
                    Book toReturn = null;
                    for (String isbn : newUser.getBookList()) {
                        Book b = library.getBookByIsbn(isbn);
                        if (b != null && b.getTitle().equalsIgnoreCase(returnBook)) {
                            toReturn = b;
                            break;
                        }
                    }
                    if (toReturn != null) {
                        library.returnBook(newUser.getId(), toReturn.getIsbn());
                    } else {
                        System.out.println("No matching book found in your borrowed list.");
                    }
                }
                else{
                    System.out.println("You don't have any books to return, please checkout a book first");
                }

            }
            else if(answer.equalsIgnoreCase("quit")){
                run = false;
            }
            else{
                System.out.println("Inalid response please try again");
            }


        }


    }
    
}
