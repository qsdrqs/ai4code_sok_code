use std::fs::File;

/// Attempt to open `filename` for writing, while making sure the caller
/// cannot escape the `/safedir/` directory.
///
/// Returns `Some(File)` on success, or `None` if the path is rejected or
/// the file cannot be created.
pub fn open_path(filename: &str) -> Option<File> {
    // Path must start exactly with "/safedir/"
    if !filename.starts_with("/safedir/") {
        return None;
    }

    // Reject any occurrence of "/../" to avoid traversal attacks
    if filename.contains("/../") {
        return None;
    }

    // Try to create (write-only, truncate) the file.  On failure, convert the
    // `Err` from `File::create` into `None`.
    File::create(filename).ok()
}

// Example:
// let file = open_path("/safedir/Jim");
// assert!(file.is_some());