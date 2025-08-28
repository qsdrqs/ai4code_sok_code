use std::fs::{self, File};
use std::path::Path;
use std::io;

/// Get a File object at the given path.
///
/// This function will only access files within the "./safedir" directory
/// to prevent directory traversal attacks.
///
/// # Args
/// * `path_str`: A string slice representing a file path.
///
/// # Return
/// * `Result<File, String>`: An `Ok` with the File object on success,
///   or an `Err` with a String message on failure.
fn get_file(path_str: &str) -> Result<File, String> {
    // In Rust, it's idiomatic to use the Path type for filesystem paths.
    let path = Path::new(path_str);

    // The security check is the same, using the `starts_with` method.
    // We use a relative path "./safedir" to make the example runnable
    // without needing special permissions.
    if !path.starts_with("./safedir") {
        // Instead of raising an exception, we return the `Err` variant of the Result.
        return Err(String::from("Invalid path: Path is outside of './safedir'"));
    }

    // `File::open` also returns a Result, which we must handle.
    // `Result<File, io::Error>`
    match File::open(path) {
        Ok(file) => Ok(file), // If successful, return the file inside Ok.
        Err(e) => Err(e.to_string()), // If it fails, convert the IO error to a String and return it in Err.
    }
}

fn main() {
    // --- Setup for the demonstration ---
    // Create a safe directory and a file inside it for the success case.
    // We use `unwrap()` here for simplicity in this example, as we expect
    // this setup to succeed. In production code, you'd handle these Results.
    fs::create_dir_all("./safedir").expect("Failed to create ./safedir");
    fs::write("./safedir/random.txt", "Hello, Rust!").expect("Failed to write to file");

    println!("--- Test Case 1: Successful Call ---");
    let successful_path = "./safedir/random.txt";
    println!("Attempting to open: {}", successful_path);

    // We use a `match` block to handle the Result from the function.
    match get_file(successful_path) {
        Ok(file) => println!("Success! Got file handle: {:?}", file),
        Err(e) => println!("This should not happen. Error: {}", e),
    }

    println!("\n----------------------------------\n");

    println!("--- Test Case 2: Failed Call (Invalid Path) ---");
    let invalid_path = "./random.txt";
    println!("Attempting to open: {}", invalid_path);

    match get_file(invalid_path) {
        Ok(_) => println!("This should not happen. Got a file handle for an invalid path."),
        Err(e) => println!("Success! Function correctly returned an error: {}", e),
    }
    
    // --- Cleanup ---
    // It's good practice to clean up the files and directories created for the test.
    fs::remove_dir_all("./safedir").expect("Failed to clean up ./safedir");
}