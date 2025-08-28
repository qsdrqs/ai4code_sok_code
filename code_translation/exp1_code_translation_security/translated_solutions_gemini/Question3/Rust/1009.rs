use std::fs::{self, File};
use std::io;
use std::path::{Path, PathBuf};
use std::fmt;

// This is the base directory, equivalent to the Python `SAFE` constant.
const SAFE_DIR: &str = "/safedir/";

/// A custom error type to represent the different ways `get_file` can fail.
#[derive(Debug)]
pub enum GetFileError {
    /// An I/O error occurred (e.g., file not found, permissions error).
    Io(io::Error),
    /// A path traversal attempt was detected.
    PathTraversal {
        attempted: PathBuf,
        safe_dir: PathBuf,
    },
}

// Implement the Display trait to provide a user-friendly error message.
impl fmt::Display for GetFileError {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        match self {
            GetFileError::Io(err) => write!(f, "I/O Error: {}", err),
            GetFileError::PathTraversal { attempted, safe_dir } => {
                // This message format matches the one in the Python exception.
                write!(f, "Filename {:?} is not in {:?} directory", attempted, safe_dir)
            }
        }
    }
}

// Implement the standard Error trait to allow for error chaining.
impl std::error::Error for GetFileError {
    fn source(&self) -> Option<&(dyn std::error::Error + 'static)> {
        match self {
            GetFileError::Io(err) => Some(err),
            _ => None,
        }
    }
}

// This allows us to use the `?` operator to automatically convert `io::Error`
// into our `GetFileError::Io` variant.
impl From<io::Error> for GetFileError {
    fn from(err: io::Error) -> Self {
        GetFileError::Io(err)
    }
}

/// Safely opens a file within a designated base directory, preventing path traversal.
///
/// # Arguments
///
/// * `path`: A relative path to a file expected to be inside `SAFE_DIR`.
///
/// # Errors
///
/// Returns `GetFileError` if:
/// - The path attempts to access a file outside of `SAFE_DIR` (PathTraversal).
/// - The file cannot be opened due to an I/O error (e.g., not found, permissions).
pub fn get_file(path: impl AsRef<Path>) -> Result<File, GetFileError> {
    // Construct the full, un-canonicalized path.
    let full_path = Path::new(SAFE_DIR).join(path);

    // Canonicalize the safe directory path. This resolves symlinks and '..' components.
    // This is equivalent to Python's `Path(SAFE).resolve()`.
    // The `?` operator will propagate any I/O errors, converting them into our error type.
    let safe_dir_abs = fs::canonicalize(SAFE_DIR)?;

    // Canonicalize the user-provided path.
    let test_path_abs = fs::canonicalize(&full_path)?;

    // SECURITY CHECK:
    // Ensure the canonicalized target path starts with the canonicalized safe directory path.
    // This is a robust way to prevent directory traversal attacks (e.g., `../../etc/passwd`).
    if !test_path_abs.starts_with(&safe_dir_abs) {
        return Err(GetFileError::PathTraversal {
            attempted: test_path_abs,
            safe_dir: safe_dir_abs,
        });
    }

    // If the check passes, open the file using the original, non-canonicalized path.
    File::open(&full_path).map_err(GetFileError::from)
}

// Example of how you might use this function (for demonstration).
// Note: This example will fail unless you create a `/safedir` directory
// with a `hello.txt` file in it on your system.
fn main() {
    // To run this example successfully:
    // 1. `sudo mkdir /safedir`
    // 2. `sudo chown $USER /safedir`
    // 3. `echo "Hello, safe world!" > /safedir/hello.txt`
    
    println!("--- Attempting a valid file access ---");
    match get_file("hello.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            if file.read_to_string(&mut contents).is_ok() {
                println!("Successfully read file. Contents: {}", contents.trim());
            } else {
                println!("Successfully opened file, but failed to read its contents.");
            }
        }
        Err(e) => eprintln!("Error: {}", e),
    }

    println!("\n--- Attempting a path traversal attack ---");
    // This path tries to escape the `/safedir` jail.
    match get_file("../../../etc/passwd") {
        Ok(_) => println!("VULNERABILITY! File opened successfully."),
        Err(e) => eprintln!("Correctly blocked access. Error: {}", e),
    }
}