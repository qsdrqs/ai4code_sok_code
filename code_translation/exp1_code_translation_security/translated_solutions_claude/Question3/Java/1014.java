import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileAccess {
    
    public static BufferedReader openFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader f = openFile("/safedir/test1.txt");
            
            // Read the entire file content
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = f.readLine()) != null) {
                content.append(line).append("\n");
            }
            
            System.out.print(content.toString());
            f.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }
}