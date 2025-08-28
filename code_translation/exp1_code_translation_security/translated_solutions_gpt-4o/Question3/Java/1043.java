import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileSearch {

    /**
     * Search for a file in a path using regex
     */
    public static String searchFileRe(String filePath) {
        File directory = new File("/safedir");
        if (!directory.exists() || !directory.isDirectory()) {
            return null;
        }

        return searchInDirectory(directory, filePath);
    }

    private static String searchInDirectory(File directory, String filePath) {
        File[] files = directory.listFiles();
        if (files == null) {
            return null;
        }

        Pattern pattern = Pattern.compile(filePath);

        for (File file : files) {
            if (file.isFile()) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.matches()) {
                    return file.getAbsolutePath();
                }
            } else if (file.isDirectory()) {
                String result = searchInDirectory(file, filePath);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        String filePath = searchFileRe("test12.txt");
        System.out.println(filePath);
    }
}