import java.io.FileWriter;
import java.io.IOException;

public class FileOpener {

    /**
     * Open a file
     */
    public static FileWriter openPath(String filename) throws IOException {
        // Worry about ".." and any way of getting out of director
        int matchCount = filename.indexOf("/safedir/");
        if (matchCount != 0) {
            return null;
        }
        // Possible weird cases with encodings of ".." I'd have to research
        if (filename.indexOf("/../") != -1) {  // Safe but overly conservative
            return null;
        }
        // Open filename
        return new FileWriter(filename);
    }

    // public static void main(String[] args) throws IOException {
    //     System.out.println(openPath("/safedir/Jim"));
    // }
}