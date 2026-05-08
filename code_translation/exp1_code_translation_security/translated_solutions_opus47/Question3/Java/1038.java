import java.io.File;

public class FileUtils {
    
    public static String getFilePath(File file) {
        return file.getName();
        // Note: The original Python code had a second `return file.path`,
        // but it was unreachable (dead code). Including it in Java would
        // cause a compile error: "unreachable statement".
    }
}