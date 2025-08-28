use std::fs::File;
use std::path::Path;

/// Open a file, but only if it's in a designated safe directory.
///
/// This function checks for directory traversal attempts.
fn open_path(filename: &str) -> Option<File> {
    // Worry about ".." and any way of getting out of the directory.
    // The Python code `filename.find("/safedir/") != 0` checks if the string
    // does NOT start at index 0. The idiomatic Rust equivalent is `starts_with`.
    if !filename.starts_with("/safedir/") {
        return None;
    }

    // Possible weird cases with encodings of ".." I'd have to research.
    // The Python code `filename.find("/../") != -1` checks for containment.
    if filename.contains("/../") { // Safe but overly conservative
        return None;
    }

    // Open filename.
    // `File::create` opens a file in write-only mode. If the file already exists,
    // its contents are truncated. If it does not exist, a new file is created.
    // It returns a `Result`, which we convert to an `Option` with `.ok()`
    // to match the Python function's behavior of returning `None` on I/O errors.
    File::create(filename).ok()
}

fn main() {
    // Create the safedir directory for the example to work.
    // The `_` ignores the Result of the operation.
    let _ = std::fs::create_dir_all("/tmp/safedir");

    // Note: On systems without a root directory (like Windows), you might want to
    // adjust the paths to be relative, e.g., "safedir/Jim". For this example,
    // we'll use a temporary directory to simulate the root path.
    let base_path = "/tmp";

    println!("--- Test Case 1: Valid Path ---");
    let valid_path = format!("{}/safedir/Jim", base_path);
    println!("Attempting to open: {}", valid_path);
    match open_path(&valid_path) {
        Some(_file) => println!("Success: Opened (and created/truncated) {}", valid_path),
        None => println!("Failure: Did not open {}", valid_path),
    }

    println!("\n--- Test Case 2: Invalid Path (Traversal Attempt) ---");
    let invalid_path = format!("{}/safedir/../evil.txt", base_path);
    println!("Attempting to open: {}", invalid_path);
    match open_path(&invalid_path) {
        Some(_file) => println!("Success: Opened (and created/truncated) {}", invalid_path),
        None => println!("Failure: Did not open {}", invalid_path),
    }

    println!("\n--- Test Case 3: Invalid Path (Outside safedir) ---");
    let another_invalid_path = format!("{}/another_dir/file.txt", base_path);
    println!("Attempting to open: {}", another_invalid_path);
    match open_path(&another_invalid_path) {
        Some(_file) => println!("Success: Opened (and created/truncated) {}", another_invalid_path),
        None => println!("Failure: Did not open {}", another_invalid_path),
    }
}