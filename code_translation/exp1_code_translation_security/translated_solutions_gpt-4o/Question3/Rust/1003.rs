use std::fs;
use std::path::{Path, PathBuf};

fn safe_open<P: AsRef<Path>>(path: P) -> Option<PathBuf> {
    // Resolve the absolute path
    let path = fs::canonicalize(path).ok()?;

    // Check if the path is inside "/safedir"
    let safedir = Path::new("/safedir");
    if !path.starts_with(safedir) {
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
    if let Some(path) = safe_open("/safedir/example.txt") {
        println!("Safe path: {:?}", path);
    } else {
        println!("Path is not safe or does not exist.");
    }
}