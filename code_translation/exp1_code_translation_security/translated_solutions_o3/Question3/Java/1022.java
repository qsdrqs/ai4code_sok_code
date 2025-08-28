import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;

public class FileUtils {

    /**
     * return file at file location
     */
    public static Reader getFile(String fileLocation) {
        // Check that the path contains “/safedir” (case-insensitive, trim spaces)
        if (fileLocation != null &&
            fileLocation.trim().toLowerCase().contains("/safedir")) {

            try {
                return new FileReader(fileLocation);   // Open the file for reading
            } catch (FileNotFoundException e) {
                // File exists but couldn’t be opened (permissions, etc.)
                e.printStackTrace();
            }
        }

        System.out.println("Provided File location is inaccessible");
        return null;    // Couldn’t open or path is outside /safedir
    }

    public static void main(String[] args) {
        System.out.println(getFile("/safedir/test1.txt"));
    }
}