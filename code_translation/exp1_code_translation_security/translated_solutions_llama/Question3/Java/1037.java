import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {

    /**
     * Validates if the provided directory path starts with '/safedir'.
     * 
     * @param dir The directory path to be validated.
     * @return True if the directory path starts with '/safedir', false otherwise.
     */
    public static boolean validateDir(String dir) {
        return dir.startsWith("/safedir");
    }

    /**
     * Creates a new file in the specified directory if the directory is valid.
     * 
     * @param dir The directory path where the file will be created.
     * @return A FileWriter object for the created file, or null if the directory is invalid.
     */
    public static FileWriter createFile(String dir) {
        if (!validateDir(dir)) {
            return null;
        }
        try {
            // Create the parent directories if they do not exist
            File file = new File(dir);
            file.getParentFile().mkdirs();
            // Create the file
            return new FileWriter(file);
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(createFile("test"));
        System.out.println(createFile("/safedir/testAgain1.txt"));
    }
}