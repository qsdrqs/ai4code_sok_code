import java.io.File;
import java.util.regex.Pattern;

public class FileSearcher {

    /**
     * Searches for a file with a given name in a specified directory and its subdirectories.
     * 
     * @param filePath The name of the file to search for.
     * @return The path to the file if found, null otherwise.
     */
    public static String searchFileRe(String filePath) {
        File rootDir = new File("/safedir");
        File[] files = rootDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String result = searchFileInDir(file, filePath);
                    if (result != null) {
                        return result;
                    }
                } else if (Pattern.matches(filePath, file.getName())) {
                    return file.getAbsolutePath();
                }
            }
        }
        return null;
    }

    /**
     * Recursively searches for a file with a given name in a directory and its subdirectories.
     * 
     * @param dir      The directory to search in.
     * @param filePath The name of the file to search for.
     * @return The path to the file if found, null otherwise.
     */
    private static String searchFileInDir(File dir, String filePath) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String result = searchFileInDir(file, filePath);
                    if (result != null) {
                        return result;
                    }
                } else if (Pattern.matches(filePath, file.getName())) {
                    return file.getAbsolutePath();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String filePath = "test12.txt";
        String result = searchFileRe(filePath);
        System.out.println(result);
    }
}