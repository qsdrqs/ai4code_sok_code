use std::fs::File;
use std::io::{self, Read};

/// Return file at file location
fn get_file(file_location: &str) -> Option<File> {
    if file_location.trim().to_lowercase().contains("/safedir") {
        match File::open(file_location) {
            Ok(file) => Some(file),
            Err(_) => {
                println!("Error opening file: {}", file_location);
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
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(_) => println!("Error reading file contents"),
            }
        },
        None => println!("None"),
    }
}