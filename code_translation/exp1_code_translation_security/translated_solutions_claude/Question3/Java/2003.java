import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {
    
    public static BufferedReader load(String pathStr) throws Exception {
        Path path = Paths.get(pathStr).toAbsolutePath().normalize();
        String[] parts = path.toString().split(File.separator);
        
        // Filter out empty parts (can occur with leading separators)
        int nonEmptyCount = 0;
        String[] filteredParts = new String[parts.length];
        for (String part : parts) {
            if (!part.isEmpty()) {
                filteredParts[nonEmptyCount++] = part;
            }
        }
        
        if (nonEmptyCount < 2 || !filteredParts[1].equals("safedir")) {
            throw new Exception("Cannot access unsafe file");
        }
        
        return new BufferedReader(new FileReader(path.toFile()));
    }
    
    public static String readAll(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append(System.lineSeparator());
        }
        reader.close();
        return content.toString();
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(readAll(load("/safedir/foo.txt")));
            System.out.println(readAll(load("/tmp/foo.txt")));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}