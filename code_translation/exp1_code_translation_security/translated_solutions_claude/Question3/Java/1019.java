import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    
    public static FileReader getFile(String path) {
        try {
            Path normalizedPath = Paths.get(path).normalize();
            String normpath = normalizedPath.toString();
            System.out.println(normpath);
            
            if (!normpath.startsWith("/safedir")) {
                System.out.println("out of bounds");
                return new FileReader("/dev/null");
            } else {
                System.out.println("opening file " + normpath);
                return new FileReader(normpath);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return null;
        }
    }
    
    public static String readFile(FileReader fileReader) {
        if (fileReader == null) {
            return "";
        }
        
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "";
        }
    }
    
    public static void main(String[] args) {
        FileReader f = getFile("/safedir/test1.txt");
        System.out.println(readFile(f));
        
        FileReader g = getFile("/dev/rand0");
        System.out.println(readFile(g));
    }
}