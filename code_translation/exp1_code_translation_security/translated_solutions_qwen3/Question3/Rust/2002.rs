use std::fs::File;
use std::io;

/// Opens a file located at `/safedir/<path>` and returns a `File` handle.
/// Returns an `io::Error` if the file cannot be opened.
fn get_file(path: &str) -> io::Result<File> {
    let filename = format!("/safedir/{}", path);
    File::open(filename)
}