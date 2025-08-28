use std::fs::File;

/// Returns `true` if the provided path
///  1. starts with "/safedir"
///  2. does **not** contain the substring "/.."
fn check_if_safe(path: &str) -> bool {
    path.starts_with("/safedir") && !path.contains("/..")
}

/// Tries to open the file at `filepath` **only** if it is deemed safe.
/// On success you get `Some(File)`, otherwise `None`.
fn get_file(filepath: &str) -> Option<File> {
    if check_if_safe(filepath) {
        File::open(filepath).ok()   // convert Result<File, _> into Option<File>
    } else {
        None
    }
}