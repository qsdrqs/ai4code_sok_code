import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileOpener {
    public static FileInputStream openFile(String path) throws FileNotFoundException {
        // Python: head, sep, tail = path.partition("/safedir")
        String sep, tail;
        int index = path.indexOf("/safedir");
        if (index == -1) {
            // separator not found: sep = "", tail = ""
            sep = "";
            tail = "";
        } else {
            // separator found
            sep = "/safedir";
            tail = path.substring(index + "/safedir".length());
        }
        
        String newpath = sep + tail;
        return new FileInputStream(newpath);
    }
}