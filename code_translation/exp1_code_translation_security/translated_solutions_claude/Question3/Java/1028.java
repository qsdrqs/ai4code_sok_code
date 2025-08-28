import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    
    public static BufferedReader openFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }
    
    public static void main(String[] args) {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";
        
        try {
            // Write to file (equivalent to Python's 'with open' context manager)
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write("Hello it's Melo");
            }
            
            // Read from file
            try (BufferedReader file = openFile(filePath)) {
                String content = Files.readString(Paths.get(filePath));
                System.out.println(content);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}