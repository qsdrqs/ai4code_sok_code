import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    /**
     * Function getFile
     *
     * Input: file path
     * Returns file as BufferedReader object
     *
     * If file is outside of directory "/safedir", return null.
     */
    public static BufferedReader getFile(String filePath) {
        if (!filePath.startsWith("/safedir")) {
            System.out.println("File must be inside /safedir");
            return null;
        }
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) {
        // Create a file and write "Hello" to it
        try (FileWriter writer = new FileWriter("/safedir/test.txt")) {
            writer.write("Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test getFile with a valid path
        BufferedReader testFile = getFile("/safedir/test.txt");
        if (testFile != null) {
            try {
                System.out.println(testFile.readLine());
                testFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Test getFile with an invalid path
        testFile = getFile("/test.txt");  // Should fail
        System.out.println(testFile);
    }
}