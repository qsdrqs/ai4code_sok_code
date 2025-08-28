use std::fs::File;
use std::io;
use std::path::Path;

// Define a function to test if a path contains "/safedir" and attempt to open it
fn test(path: &str) -> Result<File, io::Error> {
    if path.contains("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "no access"))
    }
}

// Main function
fn main() {
    // Attempt to open a file in "/safedir"
    match test("/safedir/junk") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(err) => eprintln!("Error: {}", err),
    }
}