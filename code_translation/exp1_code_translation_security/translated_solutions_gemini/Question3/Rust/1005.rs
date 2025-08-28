use std::fs::{self, File};
use std::io::{self, Read};
use std::path::{Path, PathBuf};

// In Rust, global constants are declared with `const`.
// The type `&'static str` means a string literal that lives for the entire program's duration.
const SAFE_DIR: &str = "/safedir";

/// Safely loads a file, ensuring it resides within the SAFE_DIR.
///
/// This function prevents path traversal attacks by first resolving the
/// canonical, absolute path and then checking if it starts with SAFE_DIR.
///
/// # Arguments
/// * `fname` - The filename to load.
///
/// # Returns
/// * `Some(File)` if the file is safe to load and opened successfully.
/// * `None` if the path is outside SAFE_DIR or if any error occurs.
fn safe_load_file(fname: &str) -> Option<File> {
    // 1. Parse path: In Rust, we use `std::fs::canonicalize` which is equivalent
    // to Python's `os.path.realpath`. It resolves symbolic links, `..`, etc.
    // It returns a `Result`, so we handle potential errors (e.g., file not found).
    let fpath: PathBuf = match fs::canonicalize(fname) {
        Ok(path) => path,
        Err(_) => {
            // If canonicalization fails, the path is invalid.
            println!("FAILED: Could not resolve path.");
            return None;
        }
    };

    // 2. Security Check: The `Path` type has a `starts_with` method, which is
    // a safe and idiomatic way to check if a path is a prefix of another.
    if !fpath.starts_with(SAFE_DIR) {
        println!("FAILED: Path traversal attempt detected. Path is outside the safe directory.");
        return None;
    }

    // 3. Open File: `File::open` also returns a `Result`. We can convert it
    // to an `Option` using `.ok()`, which fits our function's return type.
    // `Some(file)` on success, `None` on failure.
    File::open(fpath).ok()
}

// The `main` function is the entry point of a Rust program,
// equivalent to Python's `if __name__ == "__main__":` block.
fn main() -> io::Result<()> {
    // --- Setup for a runnable example ---
    // Create the safe directory and a test file inside it.
    let safe_dir_path = Path::new(SAFE_DIR);
    if !safe_dir_path.exists() {
        fs::create_dir(safe_dir_path)?;
    }
    let test_file_path = safe_dir_path.join("test.txt");
    fs::write(&test_file_path, "Hello from the safe file!")?;
    println!("Created test file at: {}", test_file_path.display());
    // --- End of Setup ---

    println!("\nAttempting to load a safe file...");
    // In Rust, we must handle the `Option` type. `if let` is a common
    // pattern for running code only when the Option contains a value.
    if let Some(mut f) = safe_load_file("/safedir/test.txt") {
        let mut contents = String::new();
        // Reading from a file can also fail, so we handle the Result.
        // The `?` operator here propagates any I/O errors.
        f.read_to_string(&mut contents)?;
        println!("{}", contents);

        // `f.close()` is not needed! The file is automatically closed
        // when `f` goes out of scope at the end of this `if` block.
        // This is a core Rust feature called RAII (Resource Acquisition Is Initialization).
    }

    // --- Cleanup for the example ---
    fs::remove_file(&test_file_path)?;
    fs::remove_dir(safe_dir_path)?;
    println!("\nCleaned up test file and directory.");
    // --- End of Cleanup ---

    Ok(())
}