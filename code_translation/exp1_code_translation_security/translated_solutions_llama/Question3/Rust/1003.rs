use std::path::{Path, PathBuf};

/// Returns a handle to a file inside `/safedir`. If the file does not
/// exist or is not inside `/safedir`, returns `None`. If the file is
/// a symlink to a file outside of `/safedir`, returns `None`. Must
/// be a plain file (no directories).
///
/// Returns a `PathBuf` object, which you can then use with `std::fs` functions.
pub fn safe_open(path: &str) -> Option<PathBuf> {
    let path = Path::new(path).canonicalize().ok()?;
    let safe_dir = Path::new("/safedir");

    // Check if the path is inside the safe directory
    if path.strip_prefix(safe_dir).is_err() {
        return None;
    }

    // Check if the path is a file
    if !path.is_file() {
        return None;
    }

    Some(path)
}

fn main() {
    // Example usage
    if let Some(path) = safe_open("path/to/your/file.txt") {
        println!("File path: {}", path.display());
        // You can use std::fs functions here, e.g., std::fs::read_to_string(&path)
    } else {
        println!("File not found or not inside /safedir");
    }
}