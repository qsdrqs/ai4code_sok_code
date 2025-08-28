import java.io.FileReader;
import java.io.IOException;

/**
 * This class contains a method to safely open a file from a specific directory.
 */
public class FileHandler {

    /**
     * Checks if a file path is within the "/safedir/" directory and returns a
     * FileReader object for it. This is a direct translation of the Python
     * getFileObject function.
     *
     * @param inputPath The full path to the file.
     * @return A FileReader object if the path starts with "/safedir/", otherwise null.
     * @throws IOException If the file is not found or another I/O error occurs.
     * @throws StringIndexOutOfBoundsException if the inputPath is shorter than 9 characters,
     *         mirroring Python's IndexError for slicing.
     */
    public static FileReader getFileObject(String inputPath) throws IOException {
        // Python's slicing `inputPath[0:9]` is equivalent to Java's `substring(0, 9)`.
        String firstPart = inputPath.substring(0, 9);

        // In Java, string content must be compared using the .equals() method.
        if (firstPart.equals("/safedir/")) {
            // Python's `open(path)` is analogous to creating a new FileReader in Java
            // for reading text files.
            return new FileReader(inputPath);
        }

        // Python functions implicitly return `None`. The Java equivalent is `null`.
        return null;
    }

    /**
     * The main entry point of the program, equivalent to Python's `if __name__ == "__main__"`.
     *
     * @param args Command line arguments (not used).
     * @throws IOException Propagated from getFileObject if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        // Call the method with a sample path.
        // Note: The returned FileReader is not used or closed, mirroring the
        // original Python script. In a real-world application, this would cause a
        // resource leak and should be handled with a try-with-resources block.
        getFileObject("/safedir/test.txt");
    }
}