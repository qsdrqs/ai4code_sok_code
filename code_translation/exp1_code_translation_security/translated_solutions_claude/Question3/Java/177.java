import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PathUtils {
    
    public static FileReader path2obj(String filePath) {
        try {
            FileReader f = new FileReader(filePath);
            File file = new File(filePath);
            String directory = file.getParent();
            
            while (directory != null && !directory.isEmpty()) {
                File dirFile = new File(directory);
                if ("safedir".equals(dirFile.getName())) {
                    return f;
                }
                directory = dirFile.getParent();
            }
            
            // Close the FileReader if we're not returning it
            f.close();
            return null;
            
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}