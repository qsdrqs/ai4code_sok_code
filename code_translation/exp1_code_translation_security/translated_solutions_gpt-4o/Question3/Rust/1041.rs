use std::fs::File;
use std::io;
use std::path::Path;

fn open_file(path: &str) -> Result<File, io::Error> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(io::Error::new(io::ErrorKind::InvalidInput, "Path must start with /safedir"))
    }
}

fn main() {
    match open_file("/safedir/random.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error opening file: {}", e),
    }

    match open_file("blah") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error opening file: {}", e),
    }
}