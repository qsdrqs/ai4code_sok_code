import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SafeFileLoader {

    private static final String SAFE_DIR = "/safedir";

    /**
     * Safely loads a file by checking its real path against the SAFE_DIR.
     * 
     * @param fname The name of the file to load.
     * @return A FileReader for the file if it's within the SAFE_DIR, null otherwise.
     */
    public static FileReader safeLoadFile(String fname) {
        // Get the real path of the file
        File file = new File(fname);
        String fpath = file.getCanonicalPath();

        // Check if the file path starts with SAFE_DIR
        if (!fpath.startsWith(SAFE_DIR)) {
            System.out.println("FAILED");
            return null;
        }

        try {
            // Return a FileReader for the file
            return new FileReader(file);
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        FileReader f = safeLoadFile("/safedir/test.txt");
        if (f != null) {
            try (Scanner scanner = new Scanner(f)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (Exception e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }
}