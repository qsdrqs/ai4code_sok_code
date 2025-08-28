use std::fs::{self, File};
use std::io;
use std::path::{Path, PathBuf};

/// Opens a file only if it resides within the "./safedir" directory.
///
/// # Arguments
/// * `path` - The path to the file to open.
///
/// # Returns
/// A `Result` which is:
/// - `Ok(File)`: A file handle if the path is valid and the file is opened successfully.
/// - `Err(io::Error)`: An error if the path is outside the allowed directory,
///   or if any other file system error occurs.
fn open_file(path: &str) -> io::Result<File> {
    // In Rust, we use `fs::canonicalize` to get an absolute, canonical path.
    // This is equivalent to Python's `os.path.abspath`.
    // The `?` operator will automatically propagate any errors (e.g., if the path doesn't exist).
    let absolute_path: PathBuf = fs::canonicalize(path)?;
    let safe_dir_path: PathBuf = fs::canonicalize("./safedir")?;

    // The `starts_with` method on `Path` checks if one path is a prefix of another.
    if absolute_path.starts_with(safe_dir_path) {
        // If the check passes, open the file and return the result.
        File::open(path)
    } else {
        // In Rust, we don't raise exceptions for recoverable errors.
        // Instead, we return an `Err` variant of the `Result` enum.
        // We create a new `io::Error` with a specific kind and message.
        Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Access Denied",
        ))
    }
}

fn main() {
    // --- Setup for the example ---
    // Create a safe directory and a file inside it for the success case.
    // We use a relative path `./safedir` to avoid permission issues with `/safedir`.
    fs::create_dir_all("./safedir").expect("Failed to create ./safedir");
    fs::write("./safedir/test.txt", "Hello, Rust!").expect("Failed to write to test.txt");

    // Create a file outside the safe directory for the failure case.
    fs::write("./insecure.txt", "This should not be accessible.").expect("Failed to write to insecure.txt");
    
    println!("--- Attempting to open a valid file ---");
    // Call the function with a path that should be allowed.
    match open_file("./safedir/test.txt") {
        Ok(file) => println!("Success! File opened: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }

    println!("\n--- Attempting to open an invalid file ---");
    // Call the function with a path that should be denied.
    match open_file("./insecure.txt") {
        Ok(_) => println!("This should not have happened!"),
        Err(e) => println!("Correctly failed with error: {}", e),
    }
    
    // --- Cleanup ---
    fs::remove_dir_all("./safedir").expect("Failed to clean up ./safedir");
    fs::remove_file("./insecure.txt").expect("Failed to clean up ./insecure.txt");
}