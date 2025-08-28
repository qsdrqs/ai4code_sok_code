use std::fs;
use std::path::{Path, PathBuf};

/// Return a `PathBuf` that points to a file **inside** `/safedir`.
/// – If the path cannot be canonicalised (it does not exist, we do not have
///   permissions, …) `None` is returned.
/// – If, after canonicalisation, the path does **not** lie inside `/safedir`,
///   `None` is returned (this also rules out symlinks that “escape” the
///   directory).
/// – If the canonical path is not a *regular file* (e.g. directory,
///   socket, …) `None` is returned.
///
/// On success the function yields the canonical (absolute) `PathBuf`, which
/// you can pass to `std::fs::File::open`, `std::fs::read_to_string`, etc.
pub fn safe_open<P: AsRef<Path>>(user_supplied_path: P) -> Option<PathBuf> {
    // 1. Resolve the path completely (follows symlinks and yields an absolute
    //    path).  Failure → None.
    let canonical = fs::canonicalize(&user_supplied_path).ok()?;

    // 2. Ensure that the resolved path is *inside* `/safedir`.
    //    Path::starts_with performs the same semantics as pathlib.Path.relative_to.
    let safedir = Path::new("/safedir");
    if !canonical.starts_with(safedir) {
        return None;
    }

    // 3. Make sure the path refers to an actual *file* (not directory, fifo,
    //    etc.).  Failure to stat the file → None.
    if !fs::metadata(&canonical).ok()?.is_file() {
        return None;
    }

    // 4. All checks passed ⇒ hand the canonical path back to the caller.
    Some(canonical)
}