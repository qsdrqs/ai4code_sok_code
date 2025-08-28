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
            BufferedReader reader = getFile("/safedir/random.txt");
            System.out.print(readFile(reader));
        } catch (Exception e) {
            System.out.println("Error with /safedir/random.txt");
        }
        
        try {
            BufferedReader reader = getFile("/etc/passwd");
            System.out.print(readFile(reader));
        } catch (Exception e) {
            System.out.println("Access was denied");
        }
    }
}