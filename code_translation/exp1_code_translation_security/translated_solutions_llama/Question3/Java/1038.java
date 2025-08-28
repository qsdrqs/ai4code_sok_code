import java.io.File;

public class Main {

    /**
     * Returns a string array containing the name and path of the given file.
     * 
     * @param file The file object.
     * @return A string array with the file name at index 0 and the file path at index 1.
     */
    public static String[] getFilePath(File file) {
        String fileName = file.getName();
        String filePath = file.getPath();
        return new String[] { fileName, filePath };
    }

    public static void main(String[] args) {
        File file = new File("example.txt");
        String[] fileInfo = getFilePath(file);
        System.out.println("File Name: " + fileInfo[0]);
        System.out.println("File Path: " + fileInfo[1]);
    }
}