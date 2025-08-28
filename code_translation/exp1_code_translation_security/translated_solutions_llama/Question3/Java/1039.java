public static void accessFile(String path) {
    if (!path.startsWith("/safedir")) {
        System.out.println("File cannot be accessed");
        return;
    }
    try (FileInputStream fileInputStream = new FileInputStream(path)) {
        // Use fileInputStream
        System.out.println("File accessed successfully.");
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}