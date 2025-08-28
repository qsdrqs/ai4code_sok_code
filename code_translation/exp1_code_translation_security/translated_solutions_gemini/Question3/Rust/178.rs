use std::fs::{self, File};
use std::io;
use std::path::Path;

// In Rust, global constants are defined with `const` and must have their type specified.
// `&'static str` is a reference to a string that lives for the entire duration of the program.
const ALLOWED_PATH: &'static str = "safedir";

// Rust functions require type annotations for arguments and return values.
// This function takes a string slice `&str` and returns a `Result`.
// - `Ok(File)` on success, containing the file handle.
// - `Err(String)` on failure, containing an error message.
// This is the standard way to handle operations that might fail.
fn get_file(path_str: &str) -> Result<File, String> {
    // The `Path` type is used for manipulating filesystem paths.
    let path = Path::new(path_str);

    // The `starts_with` method is a safe and idiomatic way to check for a prefix.
    // It's more robust than slicing by a fixed number of bytes.
    if !path.starts_with(ALLOWED_PATH) {
        // If the check fails, we return the `Err` variant of our `Result`.
        // We use `.to_string()` to create an owned `String` for the error.
        return Err("Not allowed to get file from this dir".to_string());
    }

    // `File::open` also returns a `Result<File, io::Error>`.
    // We use `map_err` to convert the standard I/O error into our `String` error type,
    // ensuring the function always returns the same error type.
    File::open(path).map_err(|e| e.to_string())
}

fn main() {
    // --- Setup for a runnable example ---
    // Create a dummy directory and file to test against.
    // `create_dir_all` is like `mkdir -p`.
    let base_dir = Path::new(ALLOWED_PATH);
    if !base_dir.exists() {
        fs::create_dir_all(base_dir).expect("Failed to create safedir");
    }
    let valid_path_str = "safedir/test.txt";
    // Create a dummy file to open.
    File::create(valid_path_str).expect("Failed to create test file");
    // --- End Setup ---

    println!("--- Test Case 1: Valid Path ---");
    println!("Attempting to open: '{}'", valid_path_str);

    // We use a `match` block to handle the `Result` returned by `get_file`.
    // This forces us to explicitly handle both success (`Ok`) and failure (`Err`).
    match get_file(valid_path_str) {
        Ok(file) => println!("Success! Got file handle: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }

    println!("\n--- Test Case 2: Invalid Path ---");
    let invalid_path_str = "private/secrets.txt";
    println!("Attempting to open: '{}'", invalid_path_str);

    match get_file(invalid_path_str) {
        Ok(file) => println!("Success! Got file handle: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
    
    println!("\n--- Test Case 3: File Not Found ---");
    let non_existent_path_str = "safedir/non_existent.txt";
    println!("Attempting to open: '{}'", non_existent_path_str);

    match get_file(non_existent_path_str) {
        Ok(file) => println!("Success! Got file handle: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
}