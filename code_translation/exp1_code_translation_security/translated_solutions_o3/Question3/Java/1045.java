import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Main {

    /**
     * Opens a file only if it is located inside “/safedir”.
     *
     * @param path Path to the file to open.
     * @return Reader for the requested file.
     * @throws Exception If the file is outside “/safedir” or any I/O error occurs.
     */
    public static Reader openFile(String path) throws Exception {
        // Resolve any symbolic links (“..”, etc.) to get the true, canonical path.
        File requestedFile = new File(path).getCanonicalFile();
        File safeDir       = new File("/safedir").getCanonicalFile();

        // Ensure the requested file resides within the safe directory.
        if (requestedFile.getPath().startsWith(safeDir.getPath())) {
            return new FileReader(requestedFile);
        } else {
            throw new Exception("Access Denied");
        }
    }

    public static void main(String[] args) {
        try {
            Reader reader = openFile("/safedir/test.txt");
            System.out.println(reader);          // Prints the Reader object reference
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}