use std::fs::File;
use std::io::{self, ErrorKind};
use std::path::Path;

/// Opens a file at a path relative to /safedir,
/// e.g. "hello/world" refers to /safedir/hello/world.
///
/// Returns an error if the resolved path escapes /safedir.
fn get_file(path: &str) -> io::Result<File> {
    let safe_dir = Path::new("/safedir");
    let canonical_safe_dir = safe_dir.canonicalize()?;

    // Join and canonicalize to resolve any `..` components or symlinks.
    let requested_path = safe_dir.join(path);
    let canonical_path = requested_path.canonicalize()?;

    // Reject anything that escapes /safedir (prevents path traversal).
    if !canonical_path.starts_with(&canonical_safe_dir) {
        return Err(io::Error::new(
            ErrorKind::PermissionDenied,
            "Path is outside the allowed directory",
        ));
    }

    File::open(canonical_path)
}