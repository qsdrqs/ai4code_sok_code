//! This Rust code translates the provided Python script.
//!
//! To run the tests for this code, you will need to add the `tempfile`
//! crate to your `Cargo.toml` file under `[dev-dependencies]`.
//!
//! [dev-dependencies]
//! tempfile = "3.8"

use std::fs::{self, File};
use std::io;
use std::path::{Component, Path};

/// A custom error type to provide specific reasons for failure.
#[derive(Debug)]
pub enum OpenFileError {
    /// An I/O error occurred (e.g., file not found, permission denied by OS).
    Io(io::Error),
    /// The requested path is outside the allowed "/safedir" directory.
    AccessDenied,
}

/// Implement the `From` trait to allow easy conversion from `std::io::Error`
/// to our custom `OpenFileError` using the `?` operator.
impl From<io::Error> for OpenFileError {
    fn from(err: io::Error) -> Self {
        OpenFileError::Io(err)
    }
}

/// Securely opens a file only if its canonical path is inside a "/safedir" directory.
///
/// This function prevents directory traversal attacks (`../`) by first resolving
/// the path to its canonical form.
///
/// # Arguments
///
/// * `filename` - A path-like object representing the file to open.
///
/// # Returns
///
/// * `Ok(File)` if the file is successfully and safely opened.
/// * `Err(OpenFileError::AccessDenied)` if the path is not in the allowed directory.
/// * `Err(OpenFileFileError::Io)` if the path does not exist or another I/O error occurs.
pub fn open_file(filename: impl AsRef<Path>) -> Result<File, OpenFileError> {
    // In Python, `path.resolve()` makes the path absolute and resolves `..`.
    // In Rust, `fs::canonicalize` does the same and also requires the path to exist.
    // The `?` will propagate any I/O error (like file not found) as an OpenFileError::Io.
    let canonical_path = fs::canonicalize(filename)?;

    // In Python, `path.parts` gives a tuple of components.
    // In Rust, `path.components()` returns an iterator.
    let mut components = canonical_path.components();

    // We expect an absolute path, so the first component should be the root.
    let root = components.next();
    // The component after the root is the one we need to check.
    let first_dir = components.next();

    // Check if the path starts with a root directory (e.g., `/` on Unix)
    // and if the next component is the directory named "safedir".
    if root == Some(Component::RootDir) {
        if let Some(Component::Normal(dir_name)) = first_dir {
            if dir_name == "safedir" {
                // If the check passes, open the file using the verified canonical path.
                return File::open(canonical_path).map_err(OpenFileError::Io);
            }
        }
    }

    // If the path is not in "/safedir", deny access.
    Err(OpenFileError::AccessDenied)
}

/// Tests for the `open_file` function.
/// This module is only compiled when running `cargo test`.
#[cfg(test)]
mod tests {
    use super::*; // Import the parent module's items (open_file, OpenFileError).
    use std::io::Write;
    use tempfile::tempdir; // For creating temporary directories for isolated tests.

    /// This test function replicates the logic from the Python `test` function
    /// in a controlled environment.
    #[test]
    fn test_open_file_logic() {
        // --- Setup a temporary filesystem for the test ---
        let temp_root = tempdir().expect("Failed to create temp dir");
        let root_path = temp_root.path();

        // Create `/safedir` and `/a` inside the temporary root
        let safedir_path = root_path.join("safedir");
        fs::create_dir(&safedir_path).unwrap();

        let other_dir_path = root_path.join("a");
        fs::create_dir(&other_dir_path).unwrap();

        // Create a file at `/a/b` to test the traversal case
        let traversal_target_path = other_dir_path.join("b");
        File::create(&traversal_target_path).unwrap();
        
        // Create a valid file inside `/safedir` for the success case
        let good_file_path = safedir_path.join("good.txt");
        File::create(&good_file_path).unwrap();


        // --- Test Case 1: Directory traversal attempt ---
        // Corresponds to `assert open_file("/safedir/../a/b") is None`
        // The path resolves to `/a/b`, which is not in `/safedir`.
        let traversal_path = root_path.join("safedir/../a/b");
        let result = open_file(&traversal_path);

        // We assert that the function correctly returned an AccessDenied error.
        assert!(
            matches!(result, Err(OpenFileError::AccessDenied)),
            "Expected AccessDenied, but got {:?}",
            result
        );

        // --- Test Case 2: Non-existent file ---
        // Corresponds to the `try...except` block, which asserts an error is raised.
        // This path points to a file that does not exist.
        let non_existent_path = safedir_path.join("nonexistent.txt");
        let result = open_file(&non_existent_path);

        // We assert that an I/O error was returned, specifically `NotFound`.
        match result {
            Err(OpenFileError::Io(e)) if e.kind() == io::ErrorKind::NotFound => {
                // This is the expected outcome.
            }
            _ => panic!("Expected a NotFound IO error, but got {:?}", result),
        }
        
        // --- Test Case 3: Successful file opening (Bonus) ---
        // A good test suite also checks the success path.
        let result = open_file(&good_file_path);
        assert!(result.is_ok(), "Expected successful file open, but got error: {:?}", result);
    }
}