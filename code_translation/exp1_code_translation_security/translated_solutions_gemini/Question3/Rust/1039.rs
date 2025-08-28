use std::fs::{self, File};
use std::io;
use std::path::Path;

/// An enum to represent the specific errors our function can encounter.
#[derive(Debug)]
enum FileAccessError {
    /// Error for when the path is outside the allowed directory.
    ForbiddenPath,
    /// Error for any underlying I/O issues (e.g., file not found, permissions).
    Io(io::Error),
}

/// A more robust and idiomatic way to translate the Python function.
///
/// It attempts to open a file at the given path, but only if it's inside "/safedir".
///
/// # Arguments
/// * `path_str` - A string slice representing the path to the file.
///
/// # Returns
/// * `Ok(File)` if the file is accessible and opened successfully.
/// * `Err(FileAccessError)` if the path is forbidden or an I/O error occurs.
fn return_file(path_str: &str) -> Result<File, FileAccessError> {
    // In Rust, it's better to work with Path types for file paths.
    let path = Path::new(path_str);

    // Check if the path starts with the required directory.
    if !path.starts_with("/safedir") {
        // Instead of printing, we return a specific error.
        // This allows the calling code to decide how to handle the error.
        println!("File cannot be accessed"); // Mimics the original print statement
        return Err(FileAccessError::ForbiddenPath);
    }

    // Attempt to open the file. This returns a Result that we must handle.
    // We use `map_err` to convert the standard `io::Error` into our custom error type.
    File::open(path).map_err(FileAccessError::Io)
}

// --- Example Usage ---
fn main() {
    // To make this example runnable, we'll create a dummy directory and file.
    // NOTE: On systems other than Linux/macOS, you might need to adjust the path.
    // We use a temporary directory for this example.
    let safe_dir = Path::new("/tmp/safedir");
    if !safe_dir.exists() {
        fs::create_dir(safe_dir).expect("Failed to create /tmp/safedir");
    }
    let safe_file_path = safe_dir.join("hello.txt");
    fs::write(&safe_file_path, "Hello, Rust!").expect("Failed to write to test file");

    println!("--- 1. Attempting to access a valid file ---");
    // We need to convert the PathBuf back to a string slice for the function.
    match return_file(safe_file_path.to_str().unwrap()) {
        Ok(file) => println!("Success! File opened: {:?}", file),
        Err(e) => println!("Error: {:?}", e),
    }

    println!("\n--- 2. Attempting to access a forbidden file ---");
    match return_file("/etc/hosts") {
        Ok(file) => println!("Success! File opened: {:?}", file),
        Err(e) => println!("Error handled gracefully: {:?}", e),
    }

    println!("\n--- 3. Attempting to access a non-existent file in the safe directory ---");
    match return_file("/safedir/non_existent_file.txt") {
        Ok(file) => println!("Success! File opened: {:?}", file),
        Err(e) => println!("Error handled gracefully: {:?}", e),
    }
    
    // Clean up the dummy directory and file
    fs::remove_dir_all(safe_dir).expect("Failed to clean up /tmp/safedir");
}