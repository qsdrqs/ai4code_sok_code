use std::fs::File;
use std::io;
use std::path::Path;

// Function to check if the provided path starts with "/safedir"
fn check_dir(path: &str) -> bool {
    path.starts_with("/safedir")
}

// Function to get a file object
fn get_file_object(path: &str) -> Result<File, io::Error> {
    if check_dir(path) {
        File::open(path)
    } else {
        Err(io::Error::new(io::ErrorKind::InvalidInput, "Invalid directory"))
    }
}

fn main() {
    let path = "/safedir/test1.txt";
    match get_file_object(path) {
        Ok(file_object) => {
            println!("File object obtained successfully: {:?}", file_object);
        }
        Err(err) => {
            eprintln!("Error: {}", err);
        }
    }
}