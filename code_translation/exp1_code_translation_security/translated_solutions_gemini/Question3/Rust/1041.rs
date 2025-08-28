use std::fs::{self, File};
use std::io;
use std::path::Path;

/// Opens a file only if it's located within a designated safe directory.
///
/// In Rust, functions that can fail should return a `Result` enum.
/// `Result<T, E>` has two variants:
/// - `Ok(T)`: The operation succeeded, containing the value of type `T`.
/// - `Err(E)`: The operation failed, containing an error of type `E`.
///
/// Here, T is `std::fs::File` (a file handle) and E is `Box<dyn std::error::Error>`
/// (a trait object that can hold any type of error).
fn open_file(path_str: &str) -> Result<File, Box<dyn std::error::Error>> {
    // Check if the path starts with the required prefix.
    if path_str.starts_with("/safedir") {
        // If the check passes, try to open the file.
        // File::open itself returns a Result, in this case `io::Result<File>`,
        // which is an alias for `Result<File, io::Error>`.
        // We use the `?` operator to propagate the error, automatically converting
        // it to our function's error type `Box<dyn std::error::Error>`.
        let file = File::open(path_str)?;
        Ok(file)
    } else {
        // If the check fails, we return our own custom error.
        // We create a new `io::Error` to be consistent.
        Err(Box::new(io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path must start with /safedir",
        )))
    }
}

fn main() {
    // --- DEPENDENCY SETUP ---
    // The original Python code assumes the file exists. For this Rust example
    // to run successfully, we must create the directory and file first.
    // We use `_` to ignore the `Result` of these operations for this simple example,
    // but in real code, you should handle them.
    let _ = fs::create_dir_all("/safedir");
    let _ = File::create("/safedir/random.txt");
    // Note: On some systems (like Windows or sandboxed environments), you may not have
    // permission to write to the root directory. You might need to change "/safedir"
    // to a local path like "./safedir".

    println!("--- Test Case 1: Valid Path ---");
    // In Python, a successful call returns the file object.
    // In Rust, a successful call returns an `Ok(File)`. We must handle the `Result`.
    let res1 = open_file("/safedir/random.txt");

    // `match` is the idiomatic way to handle a `Result`.
    match res1 {
        Ok(file_handle) => println!("Success! Got file handle: {:?}", file_handle),
        Err(e) => println!("Failed unexpectedly: {}", e),
    }

    println!("\n--- Test Case 2: Invalid Path ---");
    // In Python, this call would raise a `ValueError` and crash the program.
    // In Rust, it returns an `Err` variant, which we can handle gracefully
    // without crashing.
    let res2 = open_file("blah");

    match res2 {
        Ok(_) => println!("This should not have succeeded!"),
        Err(e) => println!("Correctly failed as expected! Error: {}", e),
    }
}