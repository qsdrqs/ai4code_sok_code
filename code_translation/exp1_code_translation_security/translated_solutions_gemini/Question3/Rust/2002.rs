use std::fs::{self, File};
use std::io;
use std::path::{Path, PathBuf};

/// Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
///
/// This function securely opens a file within a designated directory,
/// preventing path traversal attacks.
pub fn get_file(path: &str) -> io::Result<File> {
    let base_dir = Path::new("/safedir");

    // 1. Append the user-provided path to the base directory.
    let mut path_buf = base_dir.to_path_buf();
    path_buf.push(path);

    // 2. Canonicalize the path to resolve `.` and `..` components and follow symlinks.
    // This converts a path like `/safedir/../etc/passwd` into `/etc/passwd`.
    // We use `fs::canonicalize` which requires the path to exist.
    let real_path = fs::canonicalize(&path_buf)
        .map_err(|e| io::Error::new(e.kind(), format!("Failed to resolve path: {}", path_buf.display())))?;

    // 3. Verify that the canonicalized path is still within the base directory.
    if real_path.starts_with(base_dir) {
        // If it is, open the file and return it.
        File::open(real_path)
    } else {
        // If not, it's a path traversal attempt. Return a permission denied error.
        Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Path traversal attempt detected",
        ))
    }
}