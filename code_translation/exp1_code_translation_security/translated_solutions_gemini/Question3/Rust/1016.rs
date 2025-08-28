use std::fs::{self, File};

/// Converts a path to a file object if it's considered safe.
///
/// This function only attempts to open the file if the path string
/// contains "safedir", otherwise it returns `None`. This mimics the
/// behavior of the original Python code.
fn path_to_file(path: &str) -> Option<File> {
    // The Python `in` operator on strings checks for substring containment.
    // The equivalent in Rust is the `.contains()` method.
    if path.contains("safedir") {
        // `File::open` returns a `Result<File, io::Error>`.
        // To match the Python version which returns a file object or `None`,
        // we use `.ok()`. This converts a `Result<T, E>` into an `Option<T>`,
        // returning `Some(value)` on success and `None` on any error (e.g., file not found).
        File::open(path).ok()
    } else {
        // If the path is not in a "safe" directory, return `None`.
        None
    }
}

// The `main` function in Rust is the entry point of the program,
// equivalent to the `if __name__ == '__main__':` block in Python.
fn main() {
    // --- Setup for a runnable example ---
    // We create a dummy directory and file to test the successful case.
    let safe_dir = "safedir";
    let safe_path = "safedir/random.txt";
    let unsafe_path = "notsafedir/random.txt";

    // Create the directory and file. `.expect()` will stop the program
    // if these operations fail, which is suitable for a simple example.
    fs::create_dir_all(safe_dir)
        .expect("Setup failed: could not create ./safedir/");
    File::create(safe_path)
        .expect("Setup failed: could not create safedir/random.txt");
    // --- End of Setup ---

    // Call the function with a path that should be allowed.
    println!("Attempting to open '{}':", safe_path);
    let result1 = path_to_file(safe_path);
    // We use the debug formatter `{:?}` to print the Option<File>.
    // This will print `Some(File { ... })` on success.
    println!("{:?}", result1);

    println!(); // Add a blank line for readability

    // Call the function with a path that should be denied.
    println!("Attempting to open '{}':", unsafe_path);
    let result2 = path_to_file(unsafe_path);
    // This will print `None` because the path does not contain "safedir".
    println!("{:?}", result2);

    // --- Cleanup ---
    // It's good practice to clean up the created directory and its contents.
    fs::remove_dir_all(safe_dir)
        .expect("Cleanup failed: could not remove ./safedir/");
}