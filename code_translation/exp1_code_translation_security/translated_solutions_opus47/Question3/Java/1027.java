import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {
    
    public static BufferedReader openFile(String filePath) throws Exception {
        if (!filePath.startsWith("/safedir")) {
            throw new Exception("File is outside of /safedir");
        }
        
        try {
            BufferedReader fileObj = new BufferedReader(new FileReader(filePath));
            return fileObj;
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader path = openFile("/safedir/Jim");
        System.out.println(path);
    }
}