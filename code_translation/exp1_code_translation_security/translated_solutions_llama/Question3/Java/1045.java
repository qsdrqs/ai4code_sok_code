import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    /**
     * Opens a file for reading if its path starts with the safe directory.
     * 
     * @param path The path to the file to be opened.
     * @return A FileReader object for the file.
     * @throws Exception If the file's path does not start with the safe directory or if there's an I/O error.
     */
    public static FileReader openFile(String path) throws Exception {
        String safeDir = "/safedir";
        File file = new File(path);
        String fileAbsPath = file.getAbsolutePath();
        String safeDirAbsPath = new File(safeDir).getAbsolutePath();

        if (fileAbsPath.startsWith(safeDirAbsPath)) {
            try {
                return new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new Exception("File not found", e);
            }
        } else {
            throw new Exception("Access Denied");
        }
    }

    public static void main(String[] args) {
        try {
            FileReader fileReader = openFile("/safedir/test.txt");
            // Use the fileReader as needed
            fileReader.close(); // Don't forget to close the FileReader
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}