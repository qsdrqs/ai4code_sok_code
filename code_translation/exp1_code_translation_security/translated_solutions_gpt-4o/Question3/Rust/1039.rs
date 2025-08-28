use std::fs::File;
use std::io::{self, ErrorKind};

fn return_file(path: &str) -> Result<File, io::Error> {
    if !path.starts_with("/safedir") {
        println!("File cannot be accessed");
        return Err(io::Error::new(ErrorKind::PermissionDenied, "Access denied"));
    }
    File::open(path)
}

fn main() {
    match return_file("/safedir/example.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error opening file: {}", e),
    }
}