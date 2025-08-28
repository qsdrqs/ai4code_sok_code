import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static FileWriter returnObject(String path) {
        File file = new File(path);
        String rpath = file.getAbsolutePath();
        
        if (rpath.startsWith("/safedir")) {
            return null; // Return null instead of -1 to indicate failure
        }
        
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String fileName = "/tmp/foo";
        FileWriter fileWriter = returnObject(fileName);
        System.out.println(fileWriter);
    }
}