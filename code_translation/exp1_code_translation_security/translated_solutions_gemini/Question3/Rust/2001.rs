use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

// In Rust, global constants are declared with `const` or `static`.
// `const` is generally preferred for values known at compile time.
const SAFE_DIR: &str = "/safedir";

/// Opens a file, but only if its canonical path is within SAFE_DIR.
///
/// # Arguments
///
/// * `path` - The path to the file to open.
///
/// # Returns
///
/// * `Ok(File)` if the path is safe and the file is successfully opened.
/// * `Err(io::Error)` if the path is outside SAFE_DIR, does not exist,
///   or cannot be opened.
pub fn get_file(path: &Path) -> io::Result<File> {
    // `path.canonicalize()` resolves the path by accessing the filesystem.
    // It resolves symlinks and ".." components, making it a very secure
    // way to get the "real" path. This is stronger than Python's `os.path.normpath`.
    // The `?` operator will propagate any errors (e.g., if the path doesn't exist).
    let canonical_path = path.canonicalize()?;

    // `Path::starts_with` checks if a path is a prefix of another.
    if !canonical_path.starts_with(SAFE_DIR) {
        // In Rust, errors are returned as the `Err` variant of a `Result`.
        // We create a new `io::Error` to represent the validation failure.
        // `PermissionDenied` is a good semantic fit for a security check failure.
        return Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Path must be in SAFE_DIR",
        ));
    }

    // If the check passes, we open the file for reading.
    // The result of `File::open` is returned directly.
    File::open(path)
}