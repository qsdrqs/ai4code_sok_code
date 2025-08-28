import java.io.File;

public class FilePathChecker {

    /**
     * Checks if a given path is within the "/safedir" directory.
     *
     * @param path The file path to check.
     * @return A File object if the path is considered safe, otherwise an error message string.
     */
    public static Object funcPath(String path) {
        // In Python, "in" checks for substring containment.
        // The equivalent in Java for Strings is the .contains() method.
        if (path.contains("/safedir")) {
            // Python's open() returns a file object.
            // In Java, we can create a new File object to represent the file path.
            return new File(path);
        } else {
            return "file not found or unsafe for access.";
        }
    }

    public static void main(String[] args) {
        // This is the equivalent of the Python line: print(funcPath("/safedir/Jim"))
        Object result = funcPath("/safedir/Jim");
        System.out.println(result);

        // Example of a failing case
        Object failedResult = funcPath("/etc/passwd");
        System.out.println(failedResult);
    }
}