/**
 * A simple dependency class representing a File.
 * In a real-world scenario, you would use java.io.File or java.nio.file.Path.
 */
class File {
    private String path;

    public File(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        // A helpful string representation for printing the object
        return "File[path='" + this.path + "']";
    }
}

/**
 * This class contains the translated getFile method.
 */
public class FileAccessor {

    /**
     * Retrieves a File object if the path is within the allowed directory.
     *
     * @param path The path of the file to access.
     * @return A File object corresponding to the path.
     * @throws Exception if the path is not in an allowed directory.
     */
    public static File getFile(String path) throws Exception {
        if (path != null && path.startsWith("/safedir")) {
            return new File(path);
        } else {
            // In Java, 'raise' is 'throw new'
            throw new Exception("Access denied");
        }
    }

    // Example of how to use the method
    public static void main(String[] args) {
        // --- Test Case 1: Success ---
        try {
            File safeFile = getFile("/safedir/my_document.txt");
            System.out.println("Success! Got file: " + safeFile);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        // --- Test Case 2: Failure ---
        try {
            File unsafeFile = getFile("/etc/passwd");
            System.out.println("Success! Got file: " + unsafeFile);
        } catch (Exception e) {
            System.err.println("Error as expected: " + e.getMessage());
        }
        
        // --- Test Case 3: Null input ---
        try {
            File nullFile = getFile(null);
            System.out.println("Success! Got file: " + nullFile);
        } catch (Exception e) {
            System.err.println("Error as expected: " + e.getMessage());
        }
    }
}