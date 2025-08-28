use std::fs::File;
use std::io;
use std::path::Path;

/// Function that takes as input a string path representing a file path
fn get_file_path(path: &str) -> Option<File> {
    if Path::new(path).is_file() {
        match File::open(path) {
            Ok(file) => Some(file),
            Err(e) => {
                eprintln!("Error opening file: {}", e);
                None
            }
        }
    } else {
        None
    }
}

fn main() {
    let path = "example.txt";
    match get_file_path(path) {
        Some(file) => println!("File {} opened successfully", path),
        None => println!("File {} not found or not a file", path),
    }
}