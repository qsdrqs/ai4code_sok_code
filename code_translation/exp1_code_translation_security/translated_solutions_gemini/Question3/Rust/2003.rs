use std::fs::{self, File};
use std::io::{self, Read};
use std::path::Path;
use std::error::Error;

/// Loads a file only if it is located in a directory named "safedir"
/// directly under the filesystem root.
///
/// This function is a translation of the Python example. It demonstrates:
/// 1. Path canonicalization to prevent traversal attacks (e.g., "../").
/// 2. Path component analysis for security checks.
/// 3. Idiomatic Rust error handling with `Result` and `Box<dyn Error>`.
///
/// # Arguments
/// * `path_str` - A string slice representing the path to the file.
///
/// # Returns
/// A `Result` containing the `File` handle if successful, or a dynamic
/// error object if the path is unsafe or another I/O error occurs.
fn load(path_str: &str) -> Result<File, Box<dyn Error>> {
    // Python: path = Path(path_str).resolve()
    // In Rust, `fs::canonicalize` resolves the path to its absolute form,
    // resolving all symbolic links. It returns a `Result`, which we handle
    // with the `?` operator to propagate errors.
    let path = fs::canonicalize(path_str)?;

    // Python: if len(path.parts) < 2 or path.parts[1] != 'safedir':
    // In Rust, we get an iterator over the path's components.
    let mut components = path.components();

    // The first component is the root (`/` on Unix, `C:\` on Windows). We skip it.
    components.next();

    // We check the second component. `components.next()` returns an `Option`.
    // We use a `match` statement to check if the component exists and has the correct value.
    match components.next() {
        // The component must exist and its string representation must be "safedir".
        Some(c) if c.as_os_str() == "safedir" => {
            // The check passed. Continue to open the file.
        }
        // Any other case (no second component, or a different name) is an error.
        _ => {
            // Python: raise Exception("Cannot access unsafe file")
            // In Rust, we return an `Err` variant of the `Result`.
            return Err(From::from("Cannot access unsafe file"));
        }
    }

    // Python: return open(path)
    // `File::open` also returns a `Result`, so we use `?` again.
    Ok(File::open(path)?)
}

/// Main function to set up a test environment and run the `load` function.
fn main() -> Result<(), Box<dyn Error>> {
    // --- Setup: Create dummy files and directories for testing ---
    // This is necessary for the example to be runnable.
    fs::create_dir_all("safedir")?;
    fs::write("safedir/foo.txt", "This is a safe file.")?;
    
    fs::create_dir_all("tmp")?;
    fs::write("tmp/foo.txt", "This is an unsafe file.")?;
    // --- End Setup ---

    println!("--- Attempting to load a safe file ---");
    // Python: print(load("/safedir/foo.txt").read())
    // In Rust, we handle the `Result` explicitly.
    match load("safedir/foo.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents)?;
            println!("Success! Contents: \"{}\"", contents);
        }
        Err(e) => {
            eprintln!("Error loading safe file: {}", e);
        }
    }

    println!("\n--- Attempting to load an unsafe file ---");
    // Python: print(load("/tmp/foo.txt").read())
    match load("tmp/foo.txt") {
        Ok(_) => {
            eprintln!("Error: Loaded an unsafe file, but it should have failed!");
        }
        Err(e) => {
            println!("Correctly failed to load. Reason: {}", e);
        }
    }
    
    // Clean up the dummy files and directories
    fs::remove_dir_all("safedir")?;
    fs::remove_dir_all("tmp")?;

    Ok(())
}