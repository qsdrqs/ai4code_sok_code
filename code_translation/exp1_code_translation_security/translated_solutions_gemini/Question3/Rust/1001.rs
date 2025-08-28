// All necessary dependencies are part of the Rust standard library.
// We just need to bring them into scope with the `use` keyword.
use std::fs::File; // Used for file handling.
use std::path::Path; // Used to work with file paths.

/// Checks if a given path is safe.
/// A path is considered safe if it starts with "/safedir" and does not
/// contain any directory traversal components like "/..".
///
/// Corresponds to the Python function `checkIfSafe`.
fn check_if_safe(mystring: &str) -> bool {
    // In Rust, `starts_with` and `contains` are the equivalents of
    // Python's `startswith` and the `in` operator for strings.
    // The `&&` is a logical AND, and `!` is logical NOT.
    mystring.starts_with("/safedir") && !mystring.contains("/..")
}

/// Attempts to open a file if the path is deemed safe.
///
/// Corresponds to the Python function `getFile`.
///
/// # Arguments
/// * `filepath`: A string slice representing the path to the file.
///
/// # Returns
/// * `Some(File)` if the path is safe and the file is successfully opened.
/// * `None` if the path is unsafe OR if the file cannot be opened (e.g., does not exist).
fn get_file(filepath: &str) -> Option<File> {
    if check_if_safe(filepath) {
        // File::open returns a `Result<File, Error>`.
        // The `.ok()` method converts a `Result` into an `Option`:
        // - If the result is `Ok(file)`, it becomes `Some(file)`.
        // - If the result is `Err(error)`, it becomes `None`.
        // This perfectly mimics the Python version's behavior where an
        // I/O error would also prevent a file handle from being returned.
        File::open(filepath).ok()
    } else {
        // If the path is not safe, return None.
        None
    }
}

// A main function to demonstrate the usage of the translated code.
fn main() {
    // To test this, let's create a dummy directory and file.
    // NOTE: This will only work on Unix-like systems. On Windows,
    // the path format would need to be adjusted. For this example,
    // we assume a Unix-like environment.
    let safe_dir = Path::new("/safedir");
    if !safe_dir.exists() {
        // The `unwrap()` here is for demonstration purposes. In production code,
        // you would handle this potential error more gracefully.
        std::fs::create_dir(safe_dir).unwrap();
    }
    let safe_file_path = "/safedir/hello.txt";
    File::create(safe_file_path).unwrap();


    // --- Test Cases ---

    // 1. Safe and valid file path
    println!("Attempting to open '{}'...", safe_file_path);
    match get_file(safe_file_path) {
        Some(file) => println!("  -> Success! Got file handle: {:?}", file),
        None => println!("  -> Failed to get file."),
    }

    // 2. Unsafe path (directory traversal attempt)
    let unsafe_path = "/safedir/../secret.txt";
    println!("\nAttempting to open '{}'...", unsafe_path);
    match get_file(unsafe_path) {
        Some(_) => println!("  -> Success! (This should not happen)"),
        None => println!("  -> Failed as expected. Path is not safe."),
    }

    // 3. Safe path, but file does not exist
    let non_existent_path = "/safedir/other.txt";
    println!("\nAttempting to open '{}'...", non_existent_path);
    match get_file(non_existent_path) {
        Some(_) => println!("  -> Success! (This should not happen)"),
        None => println!("  -> Failed as expected. File does not exist."),
    }
    
    // Cleanup the created directory
    std::fs::remove_dir_all(safe_dir).unwrap();
}