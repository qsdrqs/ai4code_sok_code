use std::fs::{self, File};
use std::io;
use std::path::Path;

/// A custom error type to represent the unrecoverable "Exception" from the Python code.
#[derive(Debug)]
struct SecurityError(String);

/// Opens a file, but only if it's within the "/safedir" directory.
///
/// # Returns
///
/// This function's return type `Result<Option<File>, SecurityError>` mirrors the Python logic:
/// - `Ok(Some(file))`: Success, equivalent to returning a file object.
/// - `Ok(None)`: A recoverable error (like file not found), equivalent to returning `None`.
/// - `Err(SecurityError)`: An unrecoverable error, equivalent to `raise Exception`.
///
fn open_file(file_path: &str) -> Result<Option<File>, SecurityError> {
    // Security check: Ensure the path is within the allowed directory.
    // In Rust, it's more robust to work with the `Path` type.
    let path = Path::new(file_path);
    if !path.starts_with("/safedir") {
        // This is equivalent to Python's `raise Exception(...)`.
        // We return an `Err` to signal a critical failure.
        return Err(SecurityError(format!(
            "File is outside of /safedir: {}",
            file_path
        )));
    }

    // This block is equivalent to Python's `try...except IOError`.
    // `File::open` returns a `Result` which we can handle with a `match`.
    match File::open(path) {
        Ok(file_obj) => {
            // Successfully opened the file. Return `Some(file_obj)` inside an `Ok`.
            Ok(Some(file_obj))
        }
        Err(e) => {
            // An I/O error occurred. Check if it's a 'NotFound' error.
            if e.kind() == io::ErrorKind::NotFound {
                // This is equivalent to the `except IOError` block.
                println!("File not found");
                // Return `None` inside an `Ok` to signify a handled, non-critical failure.
                Ok(None)
            } else {
                // For other I/O errors, we could handle them differently,
                // but for this translation, we'll treat them the same as NotFound.
                println!("An I/O error occurred: {}", e);
                Ok(None)
            }
        }
    }
}

fn main() {
    // --- Setup for a runnable example ---
    // Create a dummy directory and file to make the success case work.
    // We ignore errors here for simplicity, as the focus is on the `open_file` function.
    let _ = fs::create_dir_all("/safedir");
    let _ = fs::write("/safedir/Jim", "Hello from Jim's file!");
    // --- End Setup ---

    println!("--- Test Case 1: Successful file opening ---");
    let file_path = "/safedir/Jim";
    println!("Attempting to open: {}", file_path);

    // In Rust, we explicitly handle all possible outcomes using a `match` statement.
    let result = open_file(file_path);

    // The `print(path)` in Python would show the file object or `None`.
    // Here, we print a descriptive message for each case.
    match result {
        Ok(Some(file)) => println!("Success! File object: {:?}", file),
        Ok(None) => println!("Function returned None."), // The function already printed the reason.
        Err(e) => println!("Error: {:?}", e),
    }

    println!("\n--- Test Case 2: File not found ---");
    let non_existent_path = "/safedir/DoesNotExist";
    println!("Attempting to open: {}", non_existent_path);
    match open_file(non_existent_path) {
        Ok(Some(file)) => println!("Success! File object: {:?}", file),
        Ok(None) => println!("Function returned None as expected."),
        Err(e) => println!("Error: {:?}", e),
    }

    println!("\n--- Test Case 3: Security violation ---");
    let unsafe_path = "/etc/passwd"; // A path outside of /safedir
    println!("Attempting to open: {}", unsafe_path);
    match open_file(unsafe_path) {
        Ok(Some(file)) => println!("Success! File object: {:?}", file),
        Ok(None) => println!("Function returned None."),
        Err(e) => println!("Caught expected security error: {:?}", e),
    }

    // --- Cleanup ---
    let _ = fs::remove_dir_all("/safedir");
    // --- End Cleanup ---
}