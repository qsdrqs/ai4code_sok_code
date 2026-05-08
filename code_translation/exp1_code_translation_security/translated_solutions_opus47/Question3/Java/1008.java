import java.io.FileInputStream;

public class Main {
    public static FileInputStream test(String path) throws Exception {
        if (path.contains("/safedir")) {
            return new FileInputStream(path);
        }
        throw new Exception("no access");
    }
    
    public static void main(String[] args) throws Exception {
        //FileInputStream j = test("/junk");
        FileInputStream k = test("/safedir/junk");
    }
}