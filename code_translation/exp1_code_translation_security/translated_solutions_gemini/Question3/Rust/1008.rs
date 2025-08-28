use std::fs::{self, File};
use std::io;
use std::error::Error;
use std::fmt;

// --- Custom Error Type ---
// In Rust, it's idiomatic to create a custom error enum to represent the
// specific ways our function can fail. This gives us more type safety
// than Python's generic `Exception`.
#[derive(Debug)]
enum AppError {
    /// Represents the "no access" case from the Python code.
    AccessDenied,
    /// Wraps any I/O errors that might occur when opening the file.
    Io(io::Error),
}

// --- Implement standard traits for our error type ---

// Allow our error to be displayed in a user-friendly way.
impl fmt::Display for AppError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            AppError::AccessDenied => write!(f, "Access denied: path is not in a safe directory."),
            AppError::Io(e) => write!(f, "IO Error: {}", e),
        }
    }
}

// Allow our error to be treated as a generic `Error`.
impl Error for AppError {
    fn source(&self) -> Option<&(dyn Error + 'static)> {
        match self {
            AppError::Io(e) => Some(e),
            _ => None,
        }
    }
}

// This allows us to use the `?` operator to automatically convert
// a standard `io::Error` into our `AppError::Io`.
impl From<io::Error> for AppError {
    fn from(err: io::Error) -> AppError {
        AppError::Io(err)
    }
}

/// Corresponds to the `test` function in Python.
///
/// It checks if a path is safe. If it is, it attempts to create/open a file.
/// Instead of raising an exception, it returns a `Result`.
/// - `Ok(File)` on success, containing the file handle.
/// - `Err(AppError)` on failure.
fn test(path: &str) -> Result<File, AppError> {
    if path.contains("safedir") {
        // If the path is safe, try to create the file.
        // The `?` operator handles errors: if `File::create` returns an
        // `Err`, `?` will immediately return it from this function.
        let file = File::create(path)?;
        Ok(file)
    } else {
        // If the path is not safe, return our custom error variant.
        Err(AppError::AccessDenied)
    }
}

/// The main entry point, equivalent to the Python script's top level.
fn main() {
    // To make this example runnable, we'll create the `safedir` directory
    // in the current location. The original Python code might imply `/safedir`
    // at the filesystem root, which is not good practice for an example.
    fs::create_dir_all("safedir").expect("Failed to create ./safedir directory");

    // --- Case 1: The failing case (like `j = test("/junk")`) ---
    println!("Attempting to access 'junk.txt'...");
    // We must handle the Result returned by `test`. `match` is a common way.
    match test("junk.txt") {
        Ok(_) => println!("  Success: This should not have happened!"),
        Err(e) => println!("  Failure as expected: {}", e),
    }

    println!("\n-----------------------------------\n");

    // --- Case 2: The succeeding case (like `k = test("/safedir/junk")`) ---
    let safe_path = "safedir/junk.txt";
    println!("Attempting to access '{}'...", safe_path);
    match test(safe_path) {
        Ok(file_handle) => {
            // `k` in the Python code is now `file_handle`.
            println!("  Success! File created at '{}'.", safe_path);
            // We can prove it's a real file handle by getting its metadata.
            if let Ok(metadata) = file_handle.metadata() {
                println!("  File size: {} bytes", metadata.len());
            }
        }
        Err(e) => println!("  Failure: This should have succeeded! Error: {}", e),
    }
}