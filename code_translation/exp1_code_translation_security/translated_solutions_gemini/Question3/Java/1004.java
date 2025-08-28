import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    /**
     * A Java translation of the get_file function.
     *
     * Input: file path
     * Returns a BufferedReader for the file.
     *
     * If the file path is outside of the "/safedir" directory, it prints an error
     * and returns null. This translation uses a simple `startsWith` check to mirror
     * the Python code's logic.
     *
     * @param filePath The path to the file.
     * @return A BufferedReader for the file, or null if access is denied or the file is not found.
     */
    public static BufferedReader getFile(String filePath) {
        if (filePath == null || !filePath.startsWith("/safedir")) {
            System.out.println("File must be inside /safedir");
            return null;
        }
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            // This will catch FileNotFoundException and other I/O errors.
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) {
        // Setup: Create the directory and a test file, similar to the Python script.
        // This is equivalent to:
        // import os
        // with open("/safedir/test.txt", "w") as f:
        //     f.write("Hello")
        try {
            // Create the /safedir directory. mkdirs() won't throw an error if it already exists.
            new File("/safedir").mkdirs();
            // Use try-with-resources to automatically close the writer.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("/safedir/test.txt"))) {
                writer.write("Hello");
            }
        } catch (IOException e) {
            System.err.println("Failed to set up the test file: " + e.getMessage());
            e.printStackTrace();
            return; // Exit if setup fails
        }

        // --- Test Case 1: Successful file retrieval ---
        // Equivalent to:
        // test_f = get_file("/safedir/test.txt")
        // print(test_f.readline())
        // test_f.close()
        
        // The try-with-resources statement ensures the BufferedReader is closed automatically.
        try (BufferedReader testF = getFile("/safedir/test.txt")) {
            if (testF != null) {
                System.out.println(testF.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }

        // --- Test Case 2: Failed file retrieval (path outside /safedir) ---
        // Equivalent to:
        // test_f = get_file("/test.txt")  # Should fail
        // print(test_f)
        BufferedReader failedTestF = getFile("/test.txt");
        System.out.println(failedTestF);
    }
}