use std::fs::File;
use std::path::Path;

/// Get File object at given path, don't access files outside of "/safedir"
///
/// # Arguments
///
/// * `path`: string representing a file path
///
/// # Returns
///
/// * `Result<File, std::io::Error>`: File object at path or an error if it fails
///
/// # Panics
///
/// If the path does not start with "/safedir"
fn get_file(path: &str) -> Result<File, std::io::Error> {
    if !path.starts_with("/safedir") {
        panic!("Invalid path");
    }
    File::open(path)
}

fn main() {
    // Successful call
    match get_file("/safedir/random.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(err) => println!("Error opening file: {}", err),
    }

    // Panic call
    // Note: In Rust, panics are not caught like exceptions in other languages.
    //       They are used for programming errors and can be caught with std::panic::catch_unwind.
    //       However, for simplicity and to mimic the Python behavior, we directly call get_file here.
    // get_file("/random.txt"); // This will panic

    // To handle panic, you can use std::panic::catch_unwind
    use std::panic;
    let result = panic::catch_unwind(|| get_file("/random.txt"));
    match result {
        Ok(_) => println!("No panic occurred"),
        Err(_) => println!("Panic occurred"),
    }
}