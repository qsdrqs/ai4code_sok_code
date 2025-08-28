import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    /**
     * Opens a file and returns a FileReader if the file's directory tree contains a "safedir".
     * 
     * @param filePath The path to the file to be opened.
     * @return A FileReader for the file if "safedir" is found in its directory tree, otherwise null.
     */
    public static FileReader path2obj(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return null; // File does not exist or is not a file
        }

        String directory = file.getParent();
        while (directory != null) {
            File dir = new File(directory);
            if (dir.getName().equals("safedir")) {
                try {
                    return new FileReader(file);
                } catch (FileNotFoundException e) {
                    // This should not happen since we checked if the file exists earlier
                    return null;
                }
            }
            directory = dir.getParent();
        }
        return null;
    }

    public static void main(String[] args) {
        // Example usage
        String filePath = "/path/to/your/file.txt";
        FileReader fileReader = path2obj(filePath);
        if (fileReader != null) {
            System.out.println("FileReader for " + filePath + " obtained successfully.");
        } else {
            System.out.println("Failed to obtain FileReader for " + filePath);
        }
    }
}