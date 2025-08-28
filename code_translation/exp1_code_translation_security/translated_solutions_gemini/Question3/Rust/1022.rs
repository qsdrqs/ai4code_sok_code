use std::fs::File;
use std::io;

/// Returns a file handle if the file is in a permitted directory.
///
/// In Rust, functions that can either return a value or nothing are
/// typically expressed using the `Option<T>` enum. This function returns
/// `Some(File)` on success and `None` on failure, which is safer than
/// Python's implicit return of `None`.
///
/// # Arguments
///
/// * `file_location` - A string slice that holds the path to the file.
fn get_file(file_location: &str) -> Option<File> {
    // Python's `strip()` is equivalent to `trim()` in Rust.
    // Python's `casefold()` is a comprehensive caseless comparison. For file paths,
    // `to_lowercase()` is the common and sufficient Rust equivalent.
    if file_location.trim().to_lowercase().contains("/safedir") {
        // In Python, `open()` can raise an exception if the file doesn't exist.
        // In Rust, `File::open()` returns a `Result<File, io::Error>`, which
        // prevents unexpected crashes and forces the programmer to handle potential errors.
        match File::open(file_location) {
            Ok(file) => {
                // Success: return the file handle wrapped in `Some`.
                Some(file)
            }
            Err(e) => {
                // Error: print the error and return `None`.
                // This is more robust than the original Python, which would crash.
                println!("Error opening file '{}': {}", file_location, e);
                None
            }
        }
    } else {
        println!("Provided File location is inaccessible");
        None
    }
}

fn main() {
    // To run this example successfully, you must first create the directory and file.
    // For example, on Linux/macOS:
    // mkdir -p /safedir
    // echo "hello rust" > /safedir/test1.txt
    //
    // The Python `print(file_object)` shows a string representation of the file.
    // In Rust, we use the `Debug` format specifier `{:?}` to print a
    // developer-friendly representation of the `Option<File>` result.
    println!("{:?}", get_file("/safedir/test1.txt"));

    // Example of a call that will be denied by the security check:
    // println!("{:?}", get_file("/etc/hosts"));
}