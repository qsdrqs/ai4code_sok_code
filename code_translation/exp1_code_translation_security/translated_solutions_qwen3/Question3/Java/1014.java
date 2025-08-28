import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Opens a file if the path starts with "/safedir".
     * Otherwise, throws an Exception with the message "Access denied".
     *
     * @param path The file path to check and open.
     * @return A BufferedReader for the opened file.
     * @throws Exception if the path does not start with "/safedir".
     */
    public static BufferedReader openFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }

    /**
     * Main method that attempts to open and read a file using the openFile method.
     * If the file is successfully opened, it prints the contents line by line.
     * If an exception occurs, it prints the stack trace.
     */
    public static void main(String[] args) {
        try (BufferedReader f = openFile("/safedir/test1.txt")) {
            String line;
            while ((line = f.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}