use std::fs::File;
use std::io;
use std::path::Path;

fn get_file(path: &str) -> Result<File, io::Error> {
    if path.starts_with("/safedir") {
        File::open(Path::new(path))
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Access denied"))
    }
}

fn main() {
    match get_file("/safedir/example.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
}