import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    
    public static BufferedReader getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
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
            System.out.println(readFile(getFile("/safedir/random.txt")));
        } catch (Exception e) {
            System.out.println("Error with /safedir/random.txt");
        }
        
        try {
            System.out.println(readFile(getFile("/etc/passwd")));
        } catch (Exception e) {
            System.out.println("Access was denied");
        }
    }
}