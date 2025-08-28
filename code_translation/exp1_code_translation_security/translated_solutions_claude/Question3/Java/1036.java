import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileAccess {
    
    public static BufferedReader getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            try {
                FileReader fileReader = new FileReader(path);
                return new BufferedReader(fileReader);
            } catch (IOException e) {
                throw new Exception("File not found or cannot be read: " + path);
            }
        } else {
            throw new Exception("Access denied");
        }
    }
    
    public static String readFile(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(readFile(getFile("/safedir/test1.txt")));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        try {
            System.out.println(readFile(getFile("/etc/passwd")));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}