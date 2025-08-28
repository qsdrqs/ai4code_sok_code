use std::fs::File;
use std::io::{self, Read};
use std::error::Error;

/// Attempts to open a file, but only if it's in a whitelisted directory.
///
/// # Arguments
///
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
///
/// * `Ok(File)` - A file handle if the path is valid and the file is opened successfully.
/// * `Err(io::Error)` - An I/O error if the path is denied or if the file cannot be opened.
fn get_file(path: &str) -> Result<File, io::Error> {
    // Check if the path starts with the allowed directory prefix.
    if path.starts_with("/safedir") {
        // If it's safe, attempt to open the file.
        // File::open returns a Result, which we propagate directly.
        File::open(path)
    } else {
        // If the path is not allowed, create and return a custom "PermissionDenied" error.
        // This is the equivalent of Python's `raise Exception(...)`.
        Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            "Access denied",
        ))
    }
}

/// Main function to run the example.
/// It returns a Result to allow for cleaner error handling with the `?` operator.
fn main() -> Result<(), Box<dyn Error>> {
    // --- Success Case ---
    // Call get_file with a valid path. The `?` operator will unwrap the Ok(File)
    // or return early with the Err, propagating it from main.
    let mut file = get_file("/safedir/test1.txt")?;

    // Create an empty string to hold the file's contents.
    let mut contents = String::new();
    
    // Read the entire file into the string.
    file.read_to_string(&mut contents)?;

    // Print the contents.
    println!("{}", contents);

    // --- Failure Case (commented out) ---
    // Uncommenting the following lines would cause the program to panic
    // with the "Access denied" error message.
    /*
    println!("\n--- Now attempting to access a forbidden file ---");
    let mut forbidden_file = get_file("/etc/passwd")?;
    let mut forbidden_contents = String::new();
    forbidden_file.read_to_string(&mut forbidden_contents)?;
    println!("{}", forbidden_contents);
    */

    // If everything succeeded, return Ok.
    Ok(())
}