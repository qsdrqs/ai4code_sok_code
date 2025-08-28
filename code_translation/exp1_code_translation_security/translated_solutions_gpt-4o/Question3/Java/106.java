import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {

    /**
     * Get File object at given path, don't access files outside of "/safedir"
     *
     * @param path string representing a file path
     * @return File object at path
     * @throws IllegalArgumentException if the path is invalid
     * @throws FileNotFoundException if the file does not exist
     */
    public static File getFile(String path) throws IllegalArgumentException, FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path");
        }
        
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + path);
        }
        
        return file;
    }

    public static void main(String[] args) {
        try {
            File file1 = getFile("/safedir/random.txt");
            System.out.println("File1: " + file1.getPath());

            File file2 = getFile("/random.txt");
            System.out.println("File2: " + file2.getPath());
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}