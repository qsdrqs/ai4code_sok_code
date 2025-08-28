use std::fs::{self, File};
use std::io;
use std::path::Path;
use std::error::Error;
use std::fmt;

// In Rust, we define a custom error enum to handle different failure cases.
// This is the idiomatic replacement for Python's custom exception classes.
#[derive(Debug)]
enum GetFileError {
    // This variant corresponds to the `PathNotAllowed` exception.
    // We store the invalid path for a better error message.
    PathNotAllowed(String),
    // This variant wraps standard I/O errors, like `FileNotFoundError`.
    Io(io::Error),
}

// Implement the `Display` trait to provide user-friendly error messages.
impl fmt::Display for GetFileError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            GetFileError::PathNotAllowed(path) => {
                write!(f, "filepath not allowed: '{}', only /safedir/... file paths are accessible", path)
            }
            GetFileError::Io(err) => write!(f, "I/O Error: {}", err),
        }
    }
}

// Implement the `Error` trait to allow for better integration with other libraries
// and the `?` operator.
impl Error for GetFileError {
    fn source(&self) -> Option<&(dyn Error + 'static)> {
        match self {
            GetFileError::Io(err) => Some(err),
            _ => None,
        }
    }
}

// This allows us to automatically convert a `std::io::Error` into our `GetFileError`
// using the `?` operator.
impl From<io::Error> for GetFileError {
    fn from(err: io::Error) -> GetFileError {
        GetFileError::Io(err)
    }
}

/// Checks if a path is allowed and opens the file if it is.
///
/// # Errors
///
/// Returns `GetFileError::PathNotAllowed` if the path does not start with "/safedir/".
/// Returns `GetFileError::Io` for any file system errors (e.g., not found).
fn get_file_path(path: &str) -> Result<File, GetFileError> {
    if !path.starts_with("/safedir/") {
        // Instead of raising an exception, we return the `Err` variant of our `Result`.
        return Err(GetFileError::PathNotAllowed(path.to_string()));
    }

    // `File::open` returns a `Result<File, io::Error>`.
    // The `?` operator will automatically convert the `io::Error` into our
    // `GetFileError::Io` variant (thanks to the `From` trait) and return early on failure.
    let file = File::open(path)?;
    Ok(file)
}

fn main() {
    // --- Setup: Create dummy directory and files for a runnable example ---
    const SAFE_DIR: &str = "/safedir";
    if Path::new(SAFE_DIR).exists() {
        fs::remove_dir_all(SAFE_DIR).expect("Failed to remove existing safedir for test setup");
    }
    fs::create_dir_all(SAFE_DIR).expect("Failed to create safedir for test setup");
    fs::write("/safedir/test1.txt", "hello").expect("Failed to write test1.txt");
    fs::write("/safedir/test.txt", "world").expect("Failed to write test.txt");
    println!("--- Test Setup Complete ---");

    // In Rust, we use `const` for compile-time constants.
    const PREFIX: &str = "/safedir/";

    let good_paths = [
        format!("{}{}", PREFIX, "test1.txt"),
        format!("{}{}", PREFIX, "test.txt"),
    ];

    // --- Test Case 1: Should open without exception ---
    println!("\n1. Testing good paths...");
    for path in &good_paths {
        println!("Opening '{}'", path);
        // We can use `unwrap()` here because we expect this to succeed.
        // If it fails, the program will panic, which is acceptable for a test.
        let f = get_file_path(path).unwrap();
        // In Rust, files are automatically closed when `f` goes out of scope.
        // No need for an explicit `f.close()` or a `finally` block.
    }
    println!("Successfully opened and closed good paths.");

    // --- Test Case 2: Files not existing should fail as expected ---
    println!("\n2. Testing non-existent file...");
    let non_existent_path = "/safedir/noexist.txt";
    // In Rust, `try/except` is replaced by matching on the `Result`.
    match get_file_path(non_existent_path) {
        Ok(_) => panic!("Should have failed, but file was opened!"),
        Err(GetFileError::Io(err)) if err.kind() == io::ErrorKind::NotFound => {
            // This is the expected outcome.
            println!("Successfully caught expected error: {}", err);
        }
        Err(e) => panic!("Caught wrong error type: {:?}", e),
    }

    // --- Test Case 3: Files outside of not allowed path should raise our custom error ---
    println!("\n3. Testing disallowed path...");
    let bad_path = "not/a/good/path.txt";
    match get_file_path(bad_path) {
        Ok(_) => panic!("Should have failed, but file was opened!"),
        Err(GetFileError::PathNotAllowed(path)) => {
            // This is the expected outcome.
            println!("Successfully caught expected error: PathNotAllowed for '{}'", path);
        }
        Err(e) => panic!("Caught wrong error type: {:?}", e),
    }
    
    // --- Teardown: Clean up the created directory ---
    fs::remove_dir_all(SAFE_DIR).expect("Failed to clean up safedir");
    println!("\n--- Test Teardown Complete ---");
}