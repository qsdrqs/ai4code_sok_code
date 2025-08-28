use std::fs::File;
use std::io;

/// Checks if a given file path is "safe" by ensuring it starts with `/safedir`
/// and does not contain the substring `/..`.
fn check_if_safe(mystring: &str) -> bool {
    mystring.starts_with("/safedir") && !mystring.contains("/..")
}

/// Attempts to open a file if the path is deemed safe.
/// 
/// Returns:
/// - `Ok(Some(File))` if the path is safe and the file was successfully opened.
/// - `Ok(None)` if the path is not safe.
/// - `Err(e)` if the path is safe but the file could not be opened.
pub fn get_file(filepath: &str) -> Result<Option<File>, io::Error> {
    if check_if_safe(filepath) {
        File::open(filepath).map(Some)
    } else {
        Ok(None)
    }
}