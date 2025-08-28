use std::fs::{self, File};
use std::path::Path;

/// Attempts to open a file, but only if its path contains "safedir".
///
/// In idiomatic Rust, functions that can fail return a `Result` enum.
/// - `Ok(File)` is returned on success, containing the file handle.
/// - `Err(String)` is returned on failure, containing an error message.
///
/// This approach is safer and more explicit than the Python version, which
/// returns a file handle on success or `None` on failure, with the side-effect
/// of printing an error message.
fn thing(path: &str) -> Result<File, String> {
    // In Python, `"substring" in string` checks for containment.
    // The equivalent in Rust is the `.contains()` method.
    if path.contains("safedir") {
        // `File::open` returns a `Result<File, std::io::Error>`.
        // We use `map_err` to convert the potential `io::Error` into a `String`,
        // matching our function's declared error type.
        File::open(path).map_err(|e| e.to_string())
    } else {
        // Instead of printing and returning nothing (like Python's `None`),
        // we return an `Err` variant with a descriptive message. The caller
        // can then decide how to handle this error.
        Err("invalid path".to_string())
    }
}

fn main() {
    // --- Setup for the "good path" test ---
    // The original Python code uses an absolute path. For this example to be
    // self-contained and runnable, we create the necessary files/directories locally.
    let safe_dir = Path::new("safedir");
    fs::create_dir_all(safe_dir).expect("Failed to create test directory");
    let good_path = safe_dir.join("test1.txt");
    // Create an empty file for the test to successfully open.
    File::create(&good_path).expect("Failed to create test file");

    // --- Test good path ---
    println!("#test good path");
    // We need to convert the PathBuf to a &str for the function.
    let f1 = thing(good_path.to_str().unwrap());
    println!("\nResult 1:");
    // In Rust, we explicitly handle the Result. `match` is perfect for this.
    match f1 {
        Ok(file_handle) => println!("{:?}", file_handle),
        Err(error_message) => println!("Error: {}", error_message),
    }

    // --- Test bad path ---
    println!("\n#test bad path");
    let f2 = thing("random.txt");
    println!("\nResult 2:");
    match f2 {
        Ok(file_handle) => println!("{:?}", file_handle),
        Err(error_message) => println!("Error: {}", error_message),
    }

    // --- Cleanup ---
    // Clean up the directory and file created for the test.
    fs::remove_dir_all(safe_dir).expect("Failed to clean up test directory");
}