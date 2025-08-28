use std::fs::File;
use std::io::{self, Write};
use std::path::Path;

fn open_path(filename: &str) -> Option<File> {
    // Check if the filename starts with "/safedir/"
    if !filename.starts_with("/safedir/") {
        return None;
    }
    // Check for the presence of "/../" in the filename
    if filename.contains("/../") {
        return None;
    }
    // Attempt to open the file in write mode
    match File::create(filename) {
        Ok(file) => Some(file),
        Err(_) => None,
    }
}

fn main() {
    // Example usage
    match open_path("/safedir/Jim") {
        Some(mut file) => {
            writeln!(file, "Hello, world!").expect("Failed to write to file");
        }
        None => {
            println!("Failed to open file");
        }
    }
}