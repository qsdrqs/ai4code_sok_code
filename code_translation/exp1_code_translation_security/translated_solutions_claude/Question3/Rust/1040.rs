use std::fs::File;
use std::io::Result;

/// create file object for path
fn create_file_object(path: &str) -> Option<Result<File>> {
    if path.starts_with("/safedir") {
        Some(File::open(path))
    } else {
        None
    }
}

fn main() {
    match create_file_object("/etc/passwd") {
        Some(Ok(file)) => println!("File opened successfully: {:?}", file),
        Some(Err(e)) => println!("Error opening file: {}", e),
        None => println!("None"),
    }
}