// All necessary dependencies are included.
use std::fs::File;
use std::path::Path;

/// Function that takes as input a string slice `path` representing a file path.
///
/// If the path points to an existing file, it attempts to open it for reading
/// and returns a `File` handle wrapped in `Some`.
///
/// If the path does not point to a file (e.g., it's a directory, does not exist,
/// or there's a permission error when opening), it returns `None`.
fn get_file_path(path: &str) -> Option<File> {
    // Create a Path object from the string slice to use its methods.
    let path_obj = Path::new(path);

    // First, check if the path even points to a file.
    if path_obj.is_file() {
        // If it is a file, attempt to open it.
        // `File::open` returns a `Result<File, io::Error>`.
        // The `.ok()` method is a convenient way to convert this `Result`
        // into an `Option<File>`. It transforms `Ok(file)` into `Some(file)`
        // and `Err(_)` into `None`, which perfectly matches our desired logic.
        File::open(path_obj).ok()
    } else {
        // If it's not a file (or doesn't exist), return None.
        None
    }
}

// Example of how you might use this function (optional)
fn main() {
    // Create a dummy file for demonstration.
    let _ = File::create("test.txt"); 

    // --- Case 1: File exists ---
    let path1 = "test.txt";
    match get_file_path(path1) {
        Some(file) => println!("Successfully opened file: {:?} at path '{}'", file, path1),
        None => println!("Failed to open file at path '{}'", path1),
    }

    // --- Case 2: File does not exist ---
    let path2 = "non_existent_file.txt";
    match get_file_path(path2) {
        Some(file) => println!("Successfully opened file: {:?} at path '{}'", file, path2),
        None => println!("Failed to open file at path '{}'", path2),
    }

    // --- Case 3: Path is a directory ---
    let path3 = "."; // Current directory
    match get_file_path(path3) {
        Some(file) => println!("Successfully opened file: {:?} at path '{}'", file, path3),
        None => println!("Failed to open file at path '{}' (it's a directory)", path3),
    }

    // Clean up the dummy file.
    let _ = std::fs::remove_file("test.txt");
}