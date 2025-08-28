use std::fs::{File, OpenOptions};
use std::path::Path;

/// Checks if a given directory path is safe by ensuring it starts with "/safedir".
/// In a real-world scenario, you would use more robust path handling libraries
/// to prevent directory traversal attacks (e.g., checking for "..").
fn validate_dir(dir: &str) -> bool {
    // The starts_with method on strings is a direct equivalent to Python's.
    dir.starts_with("/safedir")
}

/// Creates a file at the given path if the path is valid.
///
/// # Arguments
/// * `dir` - The path to the file to be created.
///
/// # Returns
/// * `Some(File)` if the path is valid and the file was successfully created.
/// * `None` if the path is invalid or the file could not be created.
///
/// In Rust, functions must have a single return type. Python's ability to return
/// `None` or a file handle is best represented by the `Option<T>` enum.
/// `Option<File>` means the function will either return `Some(File)` or `None`.
fn create_file(dir: &str) -> Option<File> {
    if !validate_dir(dir) {
        // If the directory is not valid, we return `None` immediately.
        return None;
    }

    // In Rust, file operations can fail (e.g., permissions error).
    // `File::create` returns a `Result<File, Error>`.
    // We use `.ok()` to convert the `Result` into an `Option`.
    // - If `File::create` succeeds (Ok(file)), `.ok()` returns `Some(file)`.
    // - If `File::create` fails (Err(error)), `.ok()` returns `None`.
    // This perfectly matches our function's return type and logic.
    File::create(dir).ok()
}

fn main() {
    // The first call will fail validation and return `None`.
    // We use `{:?}` in `println!` to print the "debug" representation of a type,
    // which works well for `Option`.
    println!("{:?}", create_file("test"));

    // The second call will pass validation. If the file can be created,
    // it will return `Some(File { ... })`.
    // Note: For this to work, the `/safedir` directory must exist on your system.
    // You can create it with `mkdir /safedir` on Linux/macOS.
    // On Windows, this path will likely cause an error unless you are using WSL.
    println!("{:?}", create_file("/safedir/testAgain1.txt"));
}