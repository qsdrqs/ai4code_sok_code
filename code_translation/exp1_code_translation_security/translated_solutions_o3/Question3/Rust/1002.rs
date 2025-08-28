use std::fs::File;
use std::io;
use std::path::{Component, Path, PathBuf};

use path_clean::PathClean;

/// The Rust equivalent of the Python `open_file` helper.
///
/// * If the cleaned-up absolute path’s first component after the root
///   is **exactly** `safedir`, the function tries to open the file and
///   returns `Ok(Some(file))`.
/// * If the path does **not** reside in `/safedir/…`, the function returns
///   `Ok(None)`.
/// * When the path is accepted but the file cannot be opened
///   (non-existent, permission denied, …) the underlying `io::Error` is
///   bubbled up – exactly like Python’s `open()` would raise an exception.
pub fn open_file<P: AsRef<Path>>(filename: P) -> io::Result<Option<File>> {
    // 1. Build an absolute path **without** touching the file-system
    //    (Python’s `resolve(strict=False)` behaviour).
    let mut path = PathBuf::from(filename.as_ref());
    if !path.is_absolute() {
        path = std::env::current_dir()?.join(path);
    }

    // 2. Normalise the path (collapse `.` and `..` segments, remove
    //    duplicate separators, …).  This does **not** require the file
    //    to exist, matching Python’s semantics.
    let resolved = path.clean();

    // 3. Inspect the components: we want “/safedir/…”.
    let mut comps = resolved.components();
    match (comps.next(), comps.next()) {
        (Some(Component::RootDir), Some(Component::Normal(dir))) if dir == "safedir" => {
            // Authorised – try to open the file and propagate any I/O error.
            File::open(&resolved).map(Some)
        }
        // Anything else is disallowed.
        _ => Ok(None),
    }
}

/* ------------------------------------------------------------------------- */
/* Tests                                                                     */
/* ------------------------------------------------------------------------- */

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn behaviour_should_match_python_version() {
        // After path normalisation the file is *not* inside /safedir,
        // therefore we must get `None`.
        assert!(open_file("/safedir/../a/b").unwrap().is_none());

        // The path *is* inside /safedir, so the function will try to open
        // the file.  Because the file almost certainly does not exist, we
        // expect an I/O error to propagate (just like Python would raise
        // `FileNotFoundError`).
        match open_file("/safedir/a") {
            Err(_) => {}             // expected
            Ok(_)  => panic!("Expected an error, but got Ok(_)"),
        }
    }
}