use std::fs::{File, canonicalize};
use std::io;
use std::path::Path;
use std::fmt;

/// Custom error types to represent the different failure modes.
/// This is necessary because Rust functions must have a single return type.
#[derive(Debug)]
enum FileOpenError {
    /// Represents the security check failure where the path is in /safedir.
    InSafeDir,
    /// Wraps standard I/O errors (e.g., file not found, permissions error).
    Io(io::Error),
    /// Represents a path that is not valid UTF-8.
    InvalidPath,
}

// Implement the Display trait for our custom error to allow for user-friendly printing.
impl fmt::Display for FileOpenError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            FileOpenError::InSafeDir => write!(f, "Attempted to access a file in a restricted directory."),
            FileOpenError::Io(err) => write!(f, "I/O Error: {}", err),
            FileOpenError::InvalidPath => write!(f, "Path is not valid UTF-8."),
        }
    }
}

// This allows our error type to be treated as a standard error.
impl std::error::Error for FileOpenError {}


/// Opens a file for writing, but only if it's not in a restricted directory.
///
/// # Arguments
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
/// * `Ok(File)` if the file was successfully opened.
/// * `Err(FileOpenError)` for any failure, encapsulating the reason.
fn return_object(path: &str) -> Result<File, FileOpenError> {
    // os.path.realpath(path) -> fs::canonicalize(path)
    // canonicalize returns a Result, which we handle immediately.
    // We map the io::Error into our custom error type.
    let rpath = canonicalize(path).map_err(FileOpenError::Io)?;

    // In Rust, we must handle the case where a path is not valid UTF-8.
    // .to_str() returns an Option<&str>, which is None if the path is not valid UTF-8.
    let rpath_str = rpath.to_str().ok_or(FileOpenError::InvalidPath)?;

    // if (rpath[:8] == "/safedir"):
    if rpath_str.starts_with("/safedir") {
        // In Python, this returned -1. In Rust, we return a specific error variant.
        return Err(FileOpenError::InSafeDir);
    }

    // try: fp = open(path, 'w')
    // File::create opens a file in write-only mode. If the file already exists,
    // its contents are truncated. If it does not exist, it is created.
    // It returns a Result, which we map to our error type on failure.
    let fp = File::create(path).map_err(FileOpenError::Io)?;

    // return fp
    Ok(fp)
}

// if __name__ == "__main__":
fn main() {
    let file_name = "/tmp/foo";

    // In Rust, we explicitly handle the Result returned by the function.
    // A `match` statement is a clear and safe way to do this.
    match return_object(file_name) {
        // Success case: The Python code would print the file object's representation.
        // We'll print a success message and the debug representation of the File struct.
        Ok(file) => {
            println!("Successfully opened file: {:?}", file);
            // The file will be automatically closed when `file` goes out of scope.
        }
        // Failure cases:
        Err(FileOpenError::InSafeDir) => {
            // This matches the `return -1` case in the Python code.
            println!("-1");
        }
        Err(e) => {
            // This handles all other errors, like the `except` block in Python.
            // We print to stderr, which is standard practice for error messages.
            eprintln!("Error: {}", e);
        }
    }
}