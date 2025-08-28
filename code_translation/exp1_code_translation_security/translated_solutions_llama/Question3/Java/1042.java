public static void getFilePath(String path) {
    File file = new File(path);
    if (file.isFile()) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            // Use fileInputStream
            System.out.println("File found and opened successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    } else {
        System.out.println("The provided path is not a file or does not exist.");
    }
}