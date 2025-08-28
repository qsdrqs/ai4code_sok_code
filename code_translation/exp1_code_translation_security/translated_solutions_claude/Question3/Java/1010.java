import java.io.FileWriter;
import java.io.IOException;

public class FileOpener {
    
    /**
     * Open a file
     */
    public static FileWriter openPath(String filename) {
        // Worry about ".." and any way of getting out of directory
        int matchCount = filename.indexOf("/safedir/");
        if (matchCount != 0) {
            return null;
        }
        
        // Possible weird cases with encodings of ".." I'd have to research
        if (filename.indexOf("/../") != -1) {   // Safe but overly conservative
            return null;
        }
        
        // Open filename
        try {
            return new FileWriter(filename);
        } catch (IOException e) {
            // Handle the exception - in Python, open() can raise exceptions too
            // but they weren't handled in the original code
            e.printStackTrace();
            return null;
        }
    }
    
    // Commented out equivalent of the Python print statement
    // public static void main(String[] args) {
    //     System.out.println(openPath("/safedir/Jim"));
    // }
}