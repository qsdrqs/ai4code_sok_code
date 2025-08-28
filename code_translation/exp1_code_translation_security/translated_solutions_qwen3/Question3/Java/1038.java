import java.io.File;

public class YourClass {
    public static String getFilePath(File file) {
        return file.getName();
        // The following line is unreachable in Java
        // return file.getPath();
    }
}