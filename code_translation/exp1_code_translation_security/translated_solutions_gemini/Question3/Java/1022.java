import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {

    /**
     * Returns a FileReader for the file at the given location if it's in a safe directory.
     *
     * @param fileLocation The path to the file.
     * @return A FileReader object if the location is accessible, otherwise null.
     * @throws FileNotFoundException if the file does not exist at the specified path.
     */
    public static FileReader getFile(String fileLocation) throws FileNotFoundException {
        // In Java, we check for null to avoid NullPointerException.
        // .trim() is equivalent to Python's .strip()
        // .toLowerCase() is the standard equivalent for Python's .casefold()
        // .contains() is equivalent to Python's 'in' operator for strings.
        if (fileLocation != null && fileLocation.trim().toLowerCase().contains("/safedir")) {
            // 'new FileReader(path)' is the Java equivalent of Python's 'open(path, 'r')' for text files.
            // It can throw a FileNotFoundException, which we declare in the method signature.
            return new FileReader(fileLocation);
        }

        // If the check fails, print a message and return null (the Java equivalent of None).
        System.out.println("Provided File location is inaccessible");
        return null;
    }

    public static void main(String[] args) {
        // The Python code prints the result of the function call.
        // In Java, we must handle potential exceptions and manage resources.
        FileReader fileReader = null;
        try {
            // Call the getFile method with a sample path.
            fileReader = getFile("/safedir/test1.txt");

            // Print the returned object, similar to the Python script.
            // If getFile returns null, this will print "null".
            // If it returns a FileReader, it will print its default string representation.
            System.out.println(fileReader);

        } catch (FileNotFoundException e) {
            // This exception is thrown by 'new FileReader' if the file doesn't exist.
            System.err.println("Error: The file was not found at the specified path.");
            e.printStackTrace();
        } finally {
            // It's crucial to close the reader to release system resources.
            // This 'finally' block ensures the reader is closed even if an error occurs.
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    // Handle potential errors during the closing of the file.
                    System.err.println("Error closing the file reader.");
                    e.printStackTrace();
                }
            }
        }
    }
}