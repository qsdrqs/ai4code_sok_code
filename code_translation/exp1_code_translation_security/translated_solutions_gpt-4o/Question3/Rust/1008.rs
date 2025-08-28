use std::fs::File;
use std::io;
use std::path::Path;

fn test(path: &str) -> io::Result<File> {
    if path.contains("/safedir") {
        return File::open(path);
    }
    Err(io::Error::new(io::ErrorKind::PermissionDenied, "no access"))
}

fn main() {
    // let j = test("/junk");
    match test("/safedir/junk") {
        Ok(file) => {
            // Use the file as needed
            println!("File opened successfully: {:?}", file);
        }
        Err(e) => {
            println!("Error: {}", e);
        }
    }
}