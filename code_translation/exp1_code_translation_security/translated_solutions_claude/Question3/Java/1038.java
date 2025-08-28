public String getFilePath(File file) {
    // Assuming we want to return name if it exists, otherwise path
    if (file.getName() != null && !file.getName().isEmpty()) {
        return file.getName();
    }
    return file.getPath();
}