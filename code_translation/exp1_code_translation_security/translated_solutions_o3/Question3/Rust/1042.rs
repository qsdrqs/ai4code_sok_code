use std::fs::File;
use std::path::Path;

/// Takes a string‐like `path` that represents a file path.
/// If the path points to an existing file, the file is opened in read-only
/// mode and returned as `Some(File)`.  
/// Otherwise, the function returns `None`.
pub fn get_file_path<P: AsRef<Path>>(path: P) -> Option<File> {
    let p = path.as_ref();

    if p.is_file() {
        File::open(p).ok()
    } else {
        None
    }
}