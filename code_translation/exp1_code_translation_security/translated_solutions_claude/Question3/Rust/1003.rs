use std::path::{Path, PathBuf};
use std::fs;

/// Return a handle to a file inside `/safedir`. If the file does not
/// exist or is not inside `/safedir`, returns `None`. If the file is
/// a symlink to a file outside of `/safedir`, returns `None`. Must
/// be a plain file (no directories).
///
/// Returns a `PathBuf` object, which you can then use with `std::fs::File::open`,
/// `std::fs::read_to_string`, or other file operations.
fn safe_open<P: AsRef<Path>>(path: P) -> Option<PathBuf> {
    let path = path.as_ref();
    
    // Resolve the path (equivalent to Python's resolve())
    let resolved_path = match path.canonicalize() {
        Ok(p) => p,
        Err(_) => return None,
    };
    
    // Check if the resolved path is inside /safedir
    let safe_dir = Path::new("/safedir");
    let safe_dir_canonical = match safe_dir.canonicalize() {
        Ok(p) => p,
        Err(_) => return None,
    };
    
    if !resolved_path.starts_with(&safe_dir_canonical) {
        return None;
    }
    
    // Check if it's a regular file (not a directory)
    match fs::metadata(&resolved_path) {
        Ok(metadata) => {
            if !metadata.is_file() {
                return None;
            }
        }
        Err(_) => return None,
    }
    
    Some(resolved_path)
}