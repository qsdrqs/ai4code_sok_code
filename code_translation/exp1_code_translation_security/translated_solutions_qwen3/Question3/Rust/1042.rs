use std::fs;
use std::path::Path;
use std::fs::File;

/// Translates the Python function `get_file_path` into Rust.
/// 
/// Returns `Some(file)` if the path is a regular file and can be opened successfully.
/// Returns `None` if the path is not a file or if opening the file fails.
pub fn get_file_path(path: &str) -> Option<File> {
    let path = Path::new(path);

    // Check if the path exists and is a regular file (following symlinks)
    if let Ok(metadata) = fs::metadata(path) {
        if metadata.is_file() {
            // Attempt to open the file
            if let Ok(file) = File::open(path) {
                return Some(file);
            }
        }
    }

    None
}