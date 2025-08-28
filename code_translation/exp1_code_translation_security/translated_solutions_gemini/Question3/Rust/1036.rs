use std::fs::{self, File};
use std::io::{self, Read};
use std::path::Path;

/// A custom error type to represent different kinds of failures.
#[derive(Debug)]
enum GetFileError {
    AccessDenied,
    Io(io::Error),
}

// This allows us to convert standard I/O errors into our custom error type easily.
impl From<io::Error> for GetFileError {
    fn from(err: io::Error) -> GetFileError {
        GetFileError::Io(err)
    }
}

/// Opens a file only if it's within the "/safedir" directory.
///
/// # Arguments
/// * `path` - The path to the file.
///
/// # Returns
/// A `Result` which is:
/// - `Ok(File)`: A file handle if the path is valid and the file opens successfully.
/// - `Err(GetFileError)`: An error if access is denied or if there's an I/O issue.
fn get_file(path: &str) -> Result<File, GetFileError> {
    if path.starts_with("/safedir") {
        // If the path is allowed, try to open the file.
        // The `?` operator propagates errors automatically.
        // If `File::open` returns an `Err`, `?` will convert it
        // into our `GetFileError::Io` and return it from `get_file`.
        let file = File::open(path)?;
        Ok(file)
    } else {
        // If the path is not allowed, return our custom "Access Denied" error.
        Err(GetFileError::AccessDenied)
    }
}

fn main() {
    // --- Setup: Create a dummy directory and file for the example to work ---
    // In a real scenario, these would already exist.
    // NOTE: On systems other than Linux/macOS, you might need to adjust the path.
    // For this example, we create it in the current directory.
    let safe_dir = Path::new("safedir");
    if !safe_dir.exists() {
        fs::create_dir(safe_dir).expect("Failed to create safedir");
    }
    let safe_file_path = safe_dir.join("test1.txt");
    fs::write(&safe_file_path, "Hello from a safe file!").expect("Failed to write to test file");
    
    // --- First Call (Success Case) ---
    println!("Attempting to access a valid file...");
    // We must handle the Result returned by `get_file`.
    match get_file(safe_file_path.to_str().unwrap()) {
        Ok(mut file) => {
            let mut contents = String::new();
            // Reading from the file can also fail, so we handle that result too.
            if let Ok(_) = file.read_to_string(&mut contents) {
                println!("Success! File contents: {}", contents.trim());
            } else {
                eprintln!("Error: Failed to read the file.");
            }
        }
        Err(e) => {
            // `eprintln!` prints to the standard error stream.
            eprintln!("Error getting file: {:?}", e);
        }
    }

    println!("\n----------------------------------------\n");

    // --- Second Call (Failure Case) ---
    println!("Attempting to access a restricted file...");
    let restricted_path = if cfg!(windows) { "C:\\Windows\\System32\\drivers\\etc\\hosts" } else { "/etc/passwd" };
    match get_file(restricted_path) {
        Ok(_) => {
            // This branch should not be reached.
            println!("This should not happen: Access was granted to a restricted file.");
        }
        Err(e) => {
            // This is the expected outcome.
            eprintln!("Successfully blocked access as expected. Error: {:?}", e);
        }
    }

    // --- Cleanup ---
    fs::remove_dir_all(safe_dir).expect("Failed to clean up safedir");
}