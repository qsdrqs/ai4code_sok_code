use std::fs::File;

/// Attempts to open `path` only if it starts with "/safedir".
/// On failure (either the prefix check or an I/O error) `None` is returned.
/// A warning message is printed when access is denied.
pub fn return_file(path: &str) -> Option<File> {
    // Enforce directory constraint
    if !path.starts_with("/safedir") {
        println!("File cannot be accessed");
        return None;
    }

    // Try to open the file; convert Result<File, _> into Option<File>
    File::open(path).ok()
}