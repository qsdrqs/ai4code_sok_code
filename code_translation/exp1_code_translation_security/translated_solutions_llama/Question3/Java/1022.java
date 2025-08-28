import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /**
     * Returns a file at the specified file location if it contains "/safedir" (case-insensitive).
     * 
     * @param fileLocation The location of the file.
     * @return A File object if the file location is accessible and contains "/safedir".
     */
    public static File getFile(String fileLocation) {
        String fileLocationLower = fileLocation.toLowerCase().trim();
        if (fileLocationLower.contains("/safedir")) {
            try {
                return new File(fileLocation);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } else {
            System.out.println("Provided File location is inaccessible");
        }
        return null;
    }

    public static void main(String[] args) {
        File file = getFile("/safedir/test1.txt");
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
    }
}