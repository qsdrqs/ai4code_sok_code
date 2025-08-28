use std::path::Path;
use std::fs::File;

// Define a custom exception for access denied
#[derive(Debug)]
struct AccessDeniedException;

impl std::error::Error for AccessDeniedException {}

impl std::fmt::Display for AccessDeniedException {
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
        write!(f, "Access denied")
    }
}

// Define a function to get a file
fn get_file(path: &str) -> Result<File, AccessDeniedException> {
    if path.starts_with("/safedir") {
        // Open the file in read-only mode
        match File::open(path) {
            Ok(file) => Ok(file),
            Err(_) => Err(AccessDeniedException),
        }
    } else {
        Err(AccessDeniedException)
    }
}

fn main() {
    // Example usage:
    let path = "/safedir/example.txt";
    match get_file(path) {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
}