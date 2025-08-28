use std::fs::File;
use std::path::Path;

/// Rough Rust equivalent of the Python `path2obj` function.
/// Returns `Some(File)` when the path lies inside a directory
/// called `safedir`, otherwise returns `None`.
pub fn path2obj<P: AsRef<Path>>(file_path: P) -> Option<File> {
    let file_path = file_path.as_ref();

    // Attempt to open the file; if that fails we mimic Python’s
    // exception by simply returning `None` here.
    let file = File::open(file_path).ok()?;

    // Start with the file’s parent directory.
    let mut dir_opt = file_path.parent();

    // Walk up the directory hierarchy.
    while let Some(dir) = dir_opt {
        if dir.file_name().map_or(false, |name| name == "safedir") {
            return Some(file); // Found the wanted directory.
        }
        dir_opt = dir.parent();
    }

    // Reached the root without finding "safedir".
    None
}