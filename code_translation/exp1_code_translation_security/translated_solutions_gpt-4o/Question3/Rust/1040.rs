use std::fs::File;
use std::io;
use std::path::Path;

fn create_file_object(path: &str) -> io::Result<Option<File>> {
    if path.starts_with("/safedir") {
        File::open(path).map(Some)
    } else {
        Ok(None)
    }
}

fn main() {
    match create_file_object("/etc/passwd") {
        Ok(Some(file)) => println!("File opened successfully: {:?}", file),
        Ok(None) => println!("Path does not start with /safedir"),
        Err(e) => println!("Error opening file: {}", e),
    }
}