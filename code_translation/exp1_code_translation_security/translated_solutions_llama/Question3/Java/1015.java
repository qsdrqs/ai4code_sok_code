import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /**
     * Returns a File object if the input path starts with "/safedir/".
     * 
     * @param inputPath The path to check and return as a File object.
     * @return A File object if the path starts with "/safedir/", otherwise null.
     */
    public static File getFileObject(String inputPath) {
        String firstPart = inputPath.substring(0, 9);
        if (firstPart.equals("/safedir/")) {
            return new File(inputPath);
        }
        return null;
    }

    public static void main(String[] args) {
        File file = getFileObject("/safedir/test.txt");
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                // Example usage: print the file content
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("File path does not start with /safedir/");
        }
    }
}