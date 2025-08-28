import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {
    
    public static FileReader openFile(String filePath) throws Exception {
        if (!filePath.startsWith("/safedir")) {
            throw new Exception("File is outside of /safedir");
        }
        
        try {
            FileReader fileObj = new FileReader(filePath);
            return fileObj;
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }
    
    public static void main(String[] args) {
        try {
            FileReader path = openFile("/safedir/Jim");
            System.out.println(path);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}