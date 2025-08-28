public class FileHandler {

    public static File getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new File(path);
        } else {
            throw new Exception("Access denied");
        }
    }
}