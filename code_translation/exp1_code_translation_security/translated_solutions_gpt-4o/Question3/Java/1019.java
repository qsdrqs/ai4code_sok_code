import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileHandler {

    public static FileReader getFile(String path) {
        File file = new File(path);
        String normpath = file.getCanonicalPath(); // Normalize the path
        System.out.println(normpath);

        if (!normpath.startsWith("/safedir")) {
            System.out.println("out of bounds");
            try {
                return new FileReader("/dev/null");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("opening file " + normpath);
            try {
                return new FileReader(normpath);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void main(String[] args) {
        try {
            FileReader f = getFile("/safedir/test1.txt");
            if (f != null) {
                BufferedReader reader = new BufferedReader(f);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            }

            FileReader g = getFile("/dev/rand0");
            if (g != null) {
                BufferedReader reader = new BufferedReader(g);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}