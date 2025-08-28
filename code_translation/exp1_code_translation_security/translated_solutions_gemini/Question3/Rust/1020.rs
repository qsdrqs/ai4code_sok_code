use std::fs::{File, create_dir_all, remove_dir, remove_file};
use std::io;

/// Attempts to open a file, but only if its path contains "/safedir".
///
/// # Arguments
/// * `path` - A string slice that holds the path to the file.
///
/// # Returns
/// * `Ok(File)` if the path is valid and the file is successfully opened.
/// * `Err(String)` if the path is deemed unsafe or the file cannot be opened.
fn func_path(path: &str) -> Result<File, String> {
  // Check if the path contains the required directory substring.
  if path.contains("/safedir") {
    // Attempt to open the file. File::open itself returns a Result.
    // We match on its result to provide a clear outcome.
    match File::open(path) {
      Ok(file) => Ok(file), // Success: return the file handle wrapped in Ok.
      Err(e) => Err(format!("File is in safedir, but could not be opened: {}", e)), // Failure: return a formatted error string.
    }
  } else {
    // If the path does not contain "/safedir", return an error.
    Err("file not found or unsafe for access.".to_string())
  }
}

fn main() {
  // --- Setup for a successful example ---
  // To make this example runnable, we create a temporary directory and file.
  let safe_path = "/safedir/Jim";
  if let Err(e) = create_dir_all("/safedir") {
      if e.kind() != io::ErrorKind::AlreadyExists {
          panic!("Failed to create directory: {}", e);
      }
  }
  // Create a dummy file to ensure File::open succeeds.
  File::create(safe_path).expect("Failed to create dummy file.");

  println!("--- Testing with a valid path ---");
  // Call the function with a path that should succeed.
  let result = func_path(safe_path);

  // Use a match statement to handle the Result.
  match result {
    Ok(file_handle) => println!("Success! Opened file: {:?}", file_handle),
    Err(error_message) => println!("Error: {}", error_message),
  }

  // --- Teardown for the successful example ---
  remove_file(safe_path).expect("Failed to remove dummy file.");
  remove_dir("/safedir").expect("Failed to remove directory.");


  println!("\n--- Testing with an invalid path ---");
  // Call the function with a path that should fail.
  let unsafe_path = "/etc/passwd";
  match func_path(unsafe_path) {
    Ok(file_handle) => println!("Success! Opened file: {:?}", file_handle),
    Err(error_message) => println!("Correctly handled error: {}", error_message),
  }
}