import java.io.File;
import java.util.regex.Pattern;

public class FileSearcher {
    
    /**
     * search file in a path
     */
    public static String searchFileRe(String filePattern) {
        return searchFileInDirectory(new File("/safedir"), filePattern);
    }
    
    /**
     * Recursively search for files matching the pattern
     */
    private static String searchFileInDirectory(File directory, String filePattern) {
        if (!directory.exists() || !directory.isDirectory()) {
            return null;
        }
        
        File[] files = directory.listFiles();
        if (files == null) {
            return null;
        }
        
        Pattern pattern = Pattern.compile(filePattern);
        
        // First check files in current directory
        for (File file : files) {
            if (file.isFile()) {
                if (pattern.matcher(file.getName()).matches()) {
                    return file.getAbsolutePath();
                }
            }
        }
        
        // Then recursively search subdirectories
        for (File file : files) {
            if (file.isDirectory()) {
                String result = searchFileInDirectory(file, filePattern);
                if (result != null) {
                    return result;
                }
            }
        }
        
        return null;
    }
    
    /**
     * main function
     */
    public static void main(String[] args) {
        String filePath = searchFileRe("test12\\.txt");
        System.out.println(filePath);
    }
}