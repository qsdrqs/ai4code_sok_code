import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    /** 
     * Mimics the Python `return_object` function.
     * 
     * @param path absolute or relative path of the file to open
     * @return  -1          -> if the canonical path starts with "/safedir"
     *          FileWriter  -> a writable file handle (analogous to Python’s open)
     *          null        -> if an exception occurs 
     */
    public static Object returnObject(String path) {
        try {
            File file = new File(path);
            String realPath = file.getCanonicalPath();   // equivalent to os.path.realpath

            if (realPath.startsWith("/safedir")) {
                return -1;                               // early-exit just like Python code
            }

            // “w” mode in Python == new FileWriter(file, false) in Java
            FileWriter writer = new FileWriter(file, false);
            return writer;

        } catch (IOException e) {                        // broad catch for file-related errors
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String fileName = "/tmp/foo";
        System.out.println(returnObject(fileName));
    }
}