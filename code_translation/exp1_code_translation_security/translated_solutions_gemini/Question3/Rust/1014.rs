// All necessary dependencies are part of Rust's standard library.
// We use `fs` for file system operations like opening and reading files.
use std::fs::{self, File};
use std::io;

/// A safe file opener that only allows access to paths inside "/safedir".
///
/// In Rust, functions that can fail should return a `Result<T, E>`,
/// where `T` is the type on success and `E` is the type on error.
/// Here, it returns a File handle on success or a String with an error message on failure.
///
/// Note: The Rust compiler will warn that this function is unused because `main`
/// does not call it. We add `#[allow(dead_code)]` to suppress this warning,
/// mirroring the Python example where `open_file` is defined but not used by `main`.
#[allow(dead_code)]
fn open_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        // Attempt to open the file. This operation itself can fail (e.g., file not found),
        // so it returns its own Result. We match on it to handle both cases.
        match File::open(path) {
            Ok(file) => Ok(file), // Success: return the file handle wrapped in Ok.
            Err(e) => Err(e.to_string()), // Failure: convert the I/O error to a String and return it.
        }
    } else {
        // The path is not in the allowed directory. Return our custom error.
        Err("Access denied".to_string())
    }
}

/// The main entry point of the program.
fn main() {
    // NOTE: The original Python code does not use its `open_file` function in the main block.
    // It calls the built-in `open()` directly. To accurately translate this behavior,
    // we will use the standard library's file reading function directly.
    // `fs::read_to_string` is a convenient way to open, read, and close a file,
    // returning its entire contents as a String.
    
    let path = "/safedir/test1.txt";

    // `fs::read_to_string` returns a Result, which we handle with a `match` statement.
    // This is the standard Rust way to handle potential errors gracefully.
    match fs::read_to_string(path) {
        Ok(contents) => {
            // If the file was read successfully, print its contents.
            println!("{}", contents);
        }
        Err(e) => {
            // If there was an error (e.g., file not found, permissions error),
            // print an error message to the standard error stream.
            // This is similar to how an unhandled Python exception would behave.
            eprintln!("Error reading file '{}': {}", path, e);
        }
    }
}