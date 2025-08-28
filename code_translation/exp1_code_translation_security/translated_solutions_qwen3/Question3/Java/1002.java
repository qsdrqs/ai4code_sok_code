import java.io.*;
import java.nio.file.*;
import java.io.BufferedReader;

public class Main {

    /**
     * Opens a file if its resolved path starts with "/safedir".
     * 
     * @param filename The path to the file.
     * @return A BufferedReader if the file is in the "safedir" directory, null otherwise.
     * @throws IOException If the file exists but cannot be opened (e.g., it's a directory).
     */
    public static BufferedReader openFile(String filename) throws IOException {
        Path path = Paths.get(filename).normalize();

        if (path.isAbsolute() && path.getNameCount() > 0) {
            String firstComponent = path.getName(0).toString();
            if ("safedir".equals(firstComponent)) {
                // Open the file and return a BufferedReader
                return Files.newBufferedReader(path);
            }
        }

        return null;
    }

    /**
     * Test method to verify the behavior of openFile.
     * 
     * - Test 1: "/safedir/../a/b" should resolve to "/a/b", which is not in "safedir", so openFile returns null.
     * - Test 2: "/safedir/a" should be in "safedir", but if it's a directory or doesn't exist, openFile throws an IOException.
     */
    public static void test() {
        // Test 1: Should return null
        try {
            BufferedReader reader = openFile("/safedir/../a/b");
            if (reader != null) {
                throw new AssertionError("Test 1 failed: expected null");
            }
        } catch (IOException e) {
            throw new AssertionError("Test 1 failed: unexpected IOException", e);
        }

        // Test 2: Should throw an exception
        try {
            BufferedReader reader = openFile("/safedir/a");
            // If opened, close and fail
            reader.close();
            throw new AssertionError("Test 2 failed: expected exception");
        } catch (IOException e) {
            // Expected
        }
    }

    /**
     * Main method to run the test.
     */
    public static void main(String[] args) {
        test();
    }
}