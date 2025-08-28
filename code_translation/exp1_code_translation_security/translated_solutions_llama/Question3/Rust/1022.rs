use std::fs::File;
use std::io;

fn get_file(file_location: &str) -> io::Result<File> {
    if file_location.to_lowercase().contains("/safedir") {
        File::open(file_location)
    } else {
        eprintln!("Provided File location is inaccessible");
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Inaccessible file location"))
    }
}

fn main() {
    match get_file("/safedir/test1.txt") {
        Ok(file) => {
            println!("File opened successfully: {:?}", file);
        }
        Err(err) => {
            println!("Error opening file: {}", err);
        }
    }
}