import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileOpener {
    
    public static BufferedReader openFile(String path) throws Exception {
        File file = new File(path);
        File safeDir = new File("/safedir");
        
        String absolutePath = file.getAbsolutePath();
        String safeDirPath = safeDir.getAbsolutePath();
        
        if (absolutePath.startsWith(safeDirPath)) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access Denied");
        }
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = openFile("/safedir/test.txt");
            
            // Read and print the file content
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}