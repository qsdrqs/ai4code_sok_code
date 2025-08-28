use std::fs::File;
use std::io::{self, Read};

/// Return file at file location
fn get_file(file_location: &str) -> Option<File> {
    if file_location.to_lowercase().trim().contains("/safedir") {
        match File::open(file_location) {
            Ok(file) => Some(file),
            Err(_) => {
                println!("Error opening the file.");
                None
            }
        }
    } else {
        println!("Provided File location is inaccessible");
        None
    }
}

fn main() {
    match get_file("/safedir/test1.txt") {
        Some(mut file) => {
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                println!("Error reading the file: {}", e);
            } else {
                println!("File contents: {}", contents);
            }
        }
        None => println!("No file returned."),
    }
}