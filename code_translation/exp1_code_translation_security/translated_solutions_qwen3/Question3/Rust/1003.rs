use std::path::{Path, PathBuf};

/// Returns a handle to a file inside `/safedir` if it is a plain file and
/// the resolved path is under `/safedir`. Returns `None` otherwise.
///
/// # Behavior
/// - Resolves symlinks and normalizes the path.
/// - Ensures the path is under `/safedir`.
/// - Ensures the path is a regular file.
///
/// # Returns
/// - `Some(PathBuf)` if all conditions are met.
/// - `None` otherwise.
pub fn safe_open<P: AsRef<Path>>(path: P) -> Option<PathBuf> {
    // Attempt to canonicalize the path (resolve symlinks and normalize)
    let canonical_path = match path.as_ref().canonicalize() {
        Ok(p) => p,
        Err(_) => return None, // Path doesn't exist or is inaccessible
    };

    // Ensure the canonical path is under `/safedir`
    if !canonical_path.starts_with("/safedir") {
        return None;
    }

    // Ensure the path is a regular file
    if !canonical_path.is_file() {
        return None;
    }

    // All checks passed
    Some(canonical_path)
}