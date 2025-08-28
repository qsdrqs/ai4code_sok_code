import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(openFile("/env.sh"));
    }

    public static FileReader openFile(String path) {
        try {
            if (inSafe(path)) {
                return new FileReader(path);
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return null;
    }

    private static boolean inSafe(String fname) {
        try {
            File safeDir = new File("/safedir").getCanonicalFile();
            File fnameFile = new File(fname).getCanonicalFile();
            File fnameParent = fnameFile.getParentFile();
            if (fnameParent == null) {
                return false;
            }
            String safePath = safeDir.getAbsolutePath();
            String parentPath = fnameParent.getAbsolutePath();
            return parentPath.equals(safePath) || parentPath.startsWith(safePath + File.separator);
        } catch (IOException e) {
            return false;
        }
    }
}