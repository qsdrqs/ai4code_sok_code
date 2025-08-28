use std::fs::File;
use std::io;
use std::path::Path;

fn get_file(path: &str) -> io::Result<File> {
    if !path.starts_with("/safedir") {
        return Err(io::Error::new(io::ErrorKind::InvalidInput, "Invalid path"));
    }
    File::open(Path::new(path))
}

fn main() {
    match get_file("/safedir/random.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error opening file: {}", e),
    }

    match get_file("/random.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error opening file: {}", e),
    }
}