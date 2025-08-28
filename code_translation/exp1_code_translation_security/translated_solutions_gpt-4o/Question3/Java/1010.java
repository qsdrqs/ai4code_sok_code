import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOpener {

    /**
     * Open a file
     */
    public static FileWriter openPath(String filename) {
        // Worry about ".." and any way of getting out of directory
        if (!filename.startsWith("/safedir/")) {
            return null;
        }
        // Possible weird cases with encodings of ".." I'd have to research
        if (filename.contains("/../")) { // Safe but overly conservative
            return null;
        }
        // Open filename
        try {
            return new FileWriter(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        FileWriter fileWriter = openPath("/safedir/Jim");
        if (fileWriter != null) {
            System.out.println("File opened successfully.");
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to open file.");
        }
    }
}