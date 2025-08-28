use std::fs::File;

fn open_path(filename: &str) -> Option<File> {
    // Check if the path starts with "/safedir/"
    if !filename.starts_with("/safedir/") {
        return None;
    }

    // Check for unsafe path traversal
    if filename.contains("/../") {
        return None;
    }

    // Attempt to create the file
    File::create(filename).ok()
}