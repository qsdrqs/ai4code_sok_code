use std::fs;
use std::path::{Path, PathBuf};

/// Return a handle to a file inside `/safedir`. If the file does not
/// exist or is not inside `/safedir`, returns `None`. If the file is
/// a symlink to a file outside of `/safedir`, returns `None`. Must
/// be a plain file (no directories).
///
/// Returns a `PathBuf` object, which you can then use to open/read
/// the file however you want.
fn safe_open<P: AsRef<Path>>(path: P) -> Option<PathBuf> {
    // `canonicalize` makes the path absolute and resolves all symlinks,
    // equivalent to `pathlib.Path.resolve()`. It returns `Err` if the
    // file does not exist, which we turn into `None`.
    let path = fs::canonicalize(path).ok()?;

    // `Path::starts_with` compares by path components (not as a string),
    // which matches the semantics of Python's `relative_to`. For example,
    // `/safedir2/foo` does NOT start with `/safedir`.
    if !path.starts_with("/safedir") {
        return None;
    }

    // Since we already canonicalized (resolving symlinks), `is_file()`
    // here checks the real target.
    if !path.is_file() {
        return None;
    }

    Some(path)
}