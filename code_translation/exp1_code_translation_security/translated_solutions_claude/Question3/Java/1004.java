import java.io.*;
import java.nio.file.*;

public class FileHandler {
    
    /**
     * Function get_file
     * 
     * Input: file path
     * Returns file as object
     * 
     * If file is outside of directory "/safedir", return.
     */
    public static BufferedReader getFile(String filePath) {
        if (!filePath.startsWith("/safedir")) {
            System.out.println("File must be inside /safedir");
            return null;
        }
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (Exception e) {
            System.out.println("File not found");
            return null;
        }
    }
    
    public static void main(String[] args) {
        try {
            // Create the file and write "Hello" to it
            FileWriter writer = new FileWriter("/safedir/test.txt");
            writer.write("Hello");
            writer.close();
            
            // Test valid file path
            BufferedReader testF = getFile("/safedir/test.txt");
            if (testF != null) {
                System.out.println(testF.readLine());
                testF.close();
            }
            
            // Test invalid file path - should fail
            BufferedReader testF2 = getFile("/test.txt");
            System.out.println(testF2);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}