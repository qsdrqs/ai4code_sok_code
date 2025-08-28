use std::fs::File;

/// Creates a file object for a given path, but only if the path starts with "/safedir".
///
/// # Arguments
///
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
///
/// * `Some(File)` if the path starts with "/safedir" and the file is successfully opened.
/// * `None` if the path does not start with "/safedir" or if the file cannot be opened.
fn create_file_object(path: &str) -> Option<File> {
    if path.starts_with("/safedir") {
        // Attempt to open the file.
        // `File::open` returns a `Result<File, io::Error>`.
        // `.ok()` converts this Result into an `Option<File>`, returning
        // `Some(file)` on success and `None` on any error (e.g., file not found).
        File::open(path).ok()
    } else {
        // If the path is not in the allowed directory, return None.
        None
    }
}

fn main() {
    // Call the function with a path that is not allowed.
    let result = create_file_object("/etc/passwd");
    
    // Print the result. The `Debug` trait implementation for `Option`
    // will print "Some(File { ... })" or "None".
    // In this case, it will print "None".
    println!("{:?}", result);
}