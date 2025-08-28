import java.io.*;
import java.nio.file.*;

public class PathToObject {
    public static FileReader path2obj(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            Path directory = Paths.get(filePath).getParent();

            while (directory != null) {
                if (directory.getFileName().toString().equals("safedir")) {
                    return fileReader;
                }
                directory = directory.getParent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}