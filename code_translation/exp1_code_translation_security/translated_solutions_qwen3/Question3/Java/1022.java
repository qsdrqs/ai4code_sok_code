import java.io.*;

public class Main {

    /**
     * Returns a BufferedReader for the specified file location if it contains "/safedir"
     * (case-insensitive and after trimming whitespace). Otherwise, prints an error message
     * and returns null.
     *
     * @param fileLocation The path to the file to be opened.
     * @return A BufferedReader for the file, or null if the path is invalid.
     * @throws IOException If an I/O error occurs while opening the file.
     */
    public static BufferedReader getFile(String fileLocation) throws IOException {
        // Convert to lowercase and trim whitespace (mimics Python's casefold().strip())
        String normalizedPath = fileLocation.toLowerCase().strip();

        // Check if the path contains "/safedir"
        if (normalizedPath.contains("/safedir")) {
            // Open the file and return a BufferedReader
            return new BufferedReader(new FileReader(fileLocation));
        } else {
            // Print error message if the path is not allowed
            System.out.println("Provided File location is inaccessible");
            return null;
        }
    }

    /**
     * Main method to demonstrate the usage of the getFile function.
     * It prints the result of getFile or catches and prints any IOException.
     */
    public static void main(String[] args) {
        try {
            // Call getFile and print the result
            System.out.println(getFile("/safedir/test1.txt"));
        } catch (IOException e) {
            // Print the exception if an I/O error occurs
            System.out.println("Error: " + e.getMessage());
        }
    }
}