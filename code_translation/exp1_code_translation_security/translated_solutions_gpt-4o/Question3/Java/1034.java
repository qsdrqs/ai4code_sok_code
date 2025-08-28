import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    public static void main(String[] args) {
        try {
            BufferedReader fileObject = getFileObject("/safedir/test1.txt");
            if (fileObject != null) {
                System.out.println("File opened successfully.");
                // You can read from the file using fileObject here
                fileObject.close(); // Don't forget to close the file
            }
        } catch (IOException e) {
            System.out.println("Error");
            System.exit(1);
        }
    }

    public static BufferedReader getFileObject(String path) throws IOException {
        if (checkDir(path)) {
            try {
                return new BufferedReader(new FileReader(path));
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(1);
            }
        } else {
            System.exit(1);
        }
        return null; // This line will never be reached, but is required for compilation
    }

    public static boolean checkDir(String path) {
        return path.startsWith("/safedir");
    }
}