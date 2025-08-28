use std::fs::File;
use std::io;
use std::path::Path;

/// Get a `File` handle for the given path, refusing to access anything
/// outside of `/safedir`.
///
/// # Arguments
/// * `path` – A string slice that holds the file path.
///
/// # Returns
/// * `Ok(File)` if the path starts with `/safedir` and the file can be opened.
/// * `Err(io::Error)` if the path is outside `/safedir` or the file cannot be opened.
fn get_file(path: &str) -> Result<File, io::Error> {
    if !path.starts_with("/safedir") {
        // Mimic Python's `ValueError` with an `io::Error` of kind `InvalidInput`.
        return Err(io::Error::new(io::ErrorKind::InvalidInput, "Invalid path"));
    }

    // Delegate to `std::fs::File::open`.
    File::open(Path::new(path))
}

fn main() {
    // This should succeed (as long as the file exists and is readable).
    match get_file("/safedir/random.txt") {
        Ok(file) => println!("Opened file successfully: {:?}", file),
        Err(e) => eprintln!("Failed to open file: {}", e),
    }

    // This should fail with our custom error.
    match get_file("/random.txt") {
        Ok(_) => println!("Unexpected success"),
        Err(e) => eprintln!("Expected error: {}", e),
    }
}