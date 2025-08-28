use std::fs::File;
use std::path::Path;

/// Attempts to open `path` only if the string contains “/safedir”.
/// Returns `Some(File)` on success, or `None` otherwise.
pub fn read_in(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        File::open(Path::new(path)).ok()
    } else {
        None
    }
}