import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileObjectCreator {

    /**
     * Create file object for path
     */
    public static Scanner createFileObject(String path) {
        if (path.startsWith("/safedir")) {
            try {
                return new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner fileScanner = createFileObject("/etc/passwd");
        if (fileScanner != null) {
            System.out.println("File opened successfully.");
            // You can read from the file using fileScanner here
            fileScanner.close();
        } else {
            System.out.println("Failed to open file.");
        }
    }
}