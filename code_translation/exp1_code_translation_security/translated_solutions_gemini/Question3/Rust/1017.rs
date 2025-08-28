// In Python, you might have a `File` class. In Rust, we use a `struct` to
// hold data. We add `#[derive(Debug)]` to allow it to be printed for debugging.
#[derive(Debug)]
pub struct File {
    // Struct fields are private by default. `pub` makes the `path` field public.
    // We use an owned `String` because the `File` struct needs to own its path data,
    // rather than just borrowing it.
    pub path: String,
}

// We can implement methods for our struct in an `impl` block.
impl File {
    // A common convention in Rust is to have a `new` function that acts as a constructor.
    pub fn new(path: &str) -> Self {
        File {
            path: path.to_string(),
        }
    }
}

/// Attempts to get a file, but only allows access to paths inside "/safedir".
///
/// # Arguments
///
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
///
/// * `Ok(File)` if the path is valid.
/// * `Err(String)` with an error message if access is denied.
fn get_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        // If the condition is met, we return a successful result
        // by wrapping the File object in `Ok()`.
        Ok(File::new(path))
    } else {
        // If the condition fails, we return an error result
        // by wrapping an error message String in `Err()`.
        // This is the Rust equivalent of raising an exception.
        Err("Access denied".to_string())
    }
}

// A `main` function to demonstrate the usage of `get_file`.
fn main() {
    // --- Test Case 1: Success ---
    let good_path = "/safedir/report.txt";
    println!("Attempting to access: {}", good_path);

    // We use a `match` statement to handle the `Result` returned by `get_file`.
    // This forces us to handle both the success and error cases at compile time.
    match get_file(good_path) {
        Ok(file) => println!("Success! Got file: {:?}", file),
        Err(e) => println!("An unexpected error occurred: {}", e),
    }

    println!("--------------------");

    // --- Test Case 2: Failure ---
    let bad_path = "/etc/shadow";
    println!("Attempting to access: {}", bad_path);

    match get_file(bad_path) {
        Ok(file) => println!("Success! Got file: {:?}", file),
        Err(e) => println!("Error as expected: {}", e),
    }
}