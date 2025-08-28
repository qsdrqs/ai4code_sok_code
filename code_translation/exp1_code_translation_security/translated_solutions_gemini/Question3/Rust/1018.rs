use std::fs::File;
use std::io::{self, Read};

/// Reads a file only if its path contains "/safedir".
///
/// # Arguments
///
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
///
/// * `Some(File)` if the path is valid and the file is successfully opened.
/// * `None` if the path does not contain "/safedir" or if the file cannot be opened.
fn read_in(path: &str) -> Option<File> {
  if path.contains("/safedir") {
    // Attempt to open the file.
    // `File::open` returns a `Result<File, io::Error>`.
    // We use `.ok()` to convert this Result into an Option:
    // - `Ok(file)` becomes `Some(file)`
    // - `Err(_)` becomes `None`
    // This safely handles potential file-opening errors (e.g., not found).
    File::open(path).ok()
  } else {
    // The path is not in the allowed directory, return None.
    None
  }
}

// --- Example Usage ---
// The following code demonstrates how to use the `read_in` function.
fn main() {
    // Create a dummy directory and file for a successful example.
    // We use `unwrap()` here for simplicity in this example setup.
    std::fs::create_dir_all("./safedir").unwrap();
    File::create("./safedir/hello.txt").unwrap();
    
    // --- Case 1: Valid path ---
    let safe_path = "./safedir/hello.txt";
    println!("Attempting to read from: {}", safe_path);
    
    // `read_in` returns an Option, so we use a `match` to handle both cases.
    match read_in(safe_path) {
        Some(mut file) => {
            let mut contents = String::new();
            // For demonstration, let's try to read from the file.
            if file.read_to_string(&mut contents).is_ok() {
                 println!("Success! Opened file handle.");
            } else {
                 println!("Successfully opened but could not read file.");
            }
        },
        None => {
            println!("Failed to get file handle. Path might be invalid or file doesn't exist.");
        }
    }

    println!("---");

    // --- Case 2: Invalid path ---
    let unsafe_path = "/etc/passwd";
    println!("Attempting to read from: {}", unsafe_path);
    
    match read_in(unsafe_path) {
        Some(_) => {
            // This branch will not be reached.
            println!("Success! Opened file handle.");
        },
        None => {
            println!("Failed to get file handle as expected due to security check.");
        }
    }
}