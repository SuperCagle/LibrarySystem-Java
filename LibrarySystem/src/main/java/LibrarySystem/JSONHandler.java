package LibrarySystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class JSONHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule()); 
    }

    public static void saveUsersToJson(Map<String, User> userCollection, String filename) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), userCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Load users from JSON using Jackson
    public static Map<String, User> loadUsersFromJson(String filename) {
        try {
            return objectMapper.readValue(new File(filename),
                objectMapper.getTypeFactory().constructMapType(Map.class, String.class, User.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>(); // fallback
        }
    }

    // Save a collection of books to a JSON file (keeps books as a Map)
    public static void saveBooksToJson(Map<String, Book> bookCollection, String filePath) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), bookCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load a collection of books from a JSON file (as a Map<String, Book>)
    public static Map<String, Book> loadBooksFromJson(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                return new HashMap<>(); 
            }
            return objectMapper.readValue(file, 
                    objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>(); 
        }
    }
}
