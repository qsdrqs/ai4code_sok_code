// All necessary dependencies from the standard library are included.
use std::fs::File;
use std::io;
use std::path::Path;
use std::ffi::OsStr;

/// Checks if a given file path is located inside a directory named "safedir"
/// anywhere in its ancestry. If it is, it returns the opened file handle.
///
/// # Arguments
///
/// * `file_path`: A generic type that can be converted into a `Path`, representing the file to check.
///
/// # Returns
///
/// This function returns a `Result` that contains an `Option`:
/// * `Ok(Some(File))`: If the file was successfully opened and is located within a "safedir".
/// * `Ok(None)`: If the file was opened, but is not within a "safedir".
/// * `Err(io::Error)`: If there was an error opening the file at `file_path` (e.g., it doesn't exist).
fn path2obj<P: AsRef<Path>>(file_path: P) -> io::Result<Option<File>> {
    // 1. `f = open(filepath, "r")`
    // In Rust, opening a file returns a `Result`. The `?` operator is used
    // to propagate any potential error, which is safer than Python's unhandled exception.
    let f = File::open(file_path.as_ref())?;

    // 2. `directory = os.path.dirname(filePath)` and the `while` loop
    // The `ancestors()` method creates an iterator that walks up the path hierarchy.
    // For a path like "/a/b/c.txt", it yields "/a/b/c.txt", then "/a/b", then "/a", then "/".
    // Since the Python code starts with the parent directory, we `.skip(1)` to ignore the file path itself.
    for directory in file_path.as_ref().ancestors().skip(1) {
        // 3. `os.path.basename(os.path.normpath(directory)) == "safedir"`
        // `file_name()` is the Rust equivalent of `os.path.basename`. It returns an `Option<&OsStr>`.
        // We compare it directly to an `OsStr` representing "safedir".
        // Note: `normpath` is not needed here as `ancestors` provides clean, canonical paths.
        if directory.file_name() == Some(OsStr::new("safedir")) {
            // 4. `return f`
            // If we find "safedir", we return the file object wrapped in `Ok(Some(...))`.
            return Ok(Some(f));
        }
    }

    // 5. `return None`
    // If the loop finishes without finding "safedir", we return `Ok(None)`.
    Ok(None)
}

// Example of how to use the function.
// To run this, you would need to create a directory structure like:
// /tmp/safedir/safe_file.txt
// /tmp/unsafe_dir/unsafe_file.txt
fn main() {
    // Setup a dummy directory and files for testing
    std::fs::create_dir_all("/tmp/safedir").unwrap();
    std::fs::create_dir_all("/tmp/unsafe_dir").unwrap();
    File::create("/tmp/safedir/safe_file.txt").unwrap();
    File::create("/tmp/unsafe_dir/unsafe_file.txt").unwrap();

    // --- Test Cases ---

    // Case 1: Path inside "safedir"
    let safe_path = "/tmp/safedir/safe_file.txt";
    println!("Checking path: {}", safe_path);
    match path2obj(safe_path) {
        Ok(Some(_file)) => println!("  -> Success: File is in a 'safedir' and was opened."),
        Ok(None) => println!("  -> Failure: File is not in a 'safedir'."),
        Err(e) => println!("  -> Error: {}", e),
    }

    // Case 2: Path not inside "safedir"
    let unsafe_path = "/tmp/unsafe_dir/unsafe_file.txt";
    println!("\nChecking path: {}", unsafe_path);
    match path2obj(unsafe_path) {
        Ok(Some(_file)) => println!("  -> Success: File is in a 'safedir' and was opened."),
        Ok(None) => println!("  -> Success: File is not in a 'safedir'."),
        Err(e) => println!("  -> Error: {}", e),
    }

    // Case 3: File does not exist
    let nonexistent_path = "/tmp/safedir/nonexistent.txt";
    println!("\nChecking path: {}", nonexistent_path);
    match path2obj(nonexistent_path) {
        Ok(Some(_file)) => println!("  -> Success: File is in a 'safedir' and was opened."),
        Ok(None) => println!("  -> Failure: File is not in a 'safedir'."),
        Err(e) => println!("  -> Success: Correctly handled error: {}", e),
    }
}