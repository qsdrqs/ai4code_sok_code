use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

// External crate that mimics Python’s `os.path.normpath` behaviour.
// (Assume it is already included in Cargo.toml.)
use path_clean::PathClean;

const SAFE_DIR: &str = "/safedir";

/// Open a file for reading, but only if the (normalized) path lives under
/// `/safedir`; otherwise return an error.
pub fn get_file<P>(path: P) -> io::Result<File>
where
    P: AsRef<Path>,
{
    // “Normalize” the path (collapse `.` / `..`, remove redundant `/`, etc.)
    // without following symlinks—similar to Python’s `os.path.normpath`.
    let normalized: PathBuf = path.as_ref().clean();

    // Ensure the normalized path is inside SAFE_DIR.
    if !normalized.starts_with(SAFE_DIR) {
        return Err(io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path must be in SAFE_DIR",
        ));
    }

    // Open the file for reading (read-only is the default mode for `File::open`).
    File::open(normalized)
}