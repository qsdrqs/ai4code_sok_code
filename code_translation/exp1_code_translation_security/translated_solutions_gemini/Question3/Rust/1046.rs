use std::fs::{self, File};
use std::io;
use std::path::Path;

/// Opens a file, but only if it's located within a "/safedir" path component.
///
/// This function mimics the Python logic:
/// 1. It finds the first occurrence of "/safedir" in the input path.
/// 2. It constructs a new path starting from "/safedir" to the end.
/// 3. It attempts to open the file at this new, sanitized path.
///
/// # Arguments
///
/// * `path`: A string slice representing the path to the file.
///
/// # Returns
///
/// * `Ok(File)`: A file handle if the path is valid and the file is opened successfully.
/// * `Err(io::Error)`: An error if the path does not contain "/safedir" or if the
///   file cannot be opened.
fn open_file(path: &str) -> io::Result<File> {
    const SEPARATOR: &str = "/safedir";

    // Python's `partition` splits a string into three parts: the part before the
    // separator, the separator itself, and the part after.
    // Rust's `split_once` is the closest equivalent, returning the parts before
    // and after the separator.
    //
    // We use `ok_or_else` to return a specific error if the separator is not found.
    let (_head, tail) = path.split_once(SEPARATOR).ok_or_else(|| {
        io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path does not contain the '/safedir' component",
        )
    })?;

    // The Python code reconstructs the path from the separator onwards.
    // We do the same here using `format!`.
    let new_path = format!("{}{}", SEPARATOR, tail);

    // `File::open` attempts to open the file and returns a `Result`.
    // The `?` operator will automatically return the `Err` if the file
    // can't be opened, otherwise it unwraps the `Ok(File)`.
    File::open(new_path)
}

// --- Example Usage ---
// The main function sets up a temporary directory and file to demonstrate
// how `open_file` works in both success and failure cases.
fn main() {
    // 1. Setup a temporary directory structure for a valid test case.
    //    ./safedir/hello.txt
    let base_dir = Path::new("safedir");
    if !base_dir.exists() {
        fs::create_dir(base_dir).expect("Failed to create safedir");
    }
    fs::write(base_dir.join("hello.txt"), "Hello, Rust!")
        .expect("Failed to write to test file");

    // 2. Test Case 1: A valid path that should succeed.
    let valid_path = "some/prefix/safedir/hello.txt";
    println!("Attempting to open valid path: '{}'", valid_path);
    match open_file(valid_path) {
        Ok(file) => println!("Success! File opened: {:?}", file),
        Err(e) => println!("Failed: {}", e),
    }

    println!("---");

    // 3. Test Case 2: An invalid path that should fail.
    let invalid_path = "some/other/dir/file.txt";
    println!("Attempting to open invalid path: '{}'", invalid_path);
    match open_file(invalid_path) {
        Ok(_) => println!("This should not have succeeded."),
        Err(e) => println!("Failed as expected: {}", e),
    }

    // 4. Cleanup the created directory and file.
    fs::remove_dir_all(base_dir).expect("Failed to clean up safedir");
}