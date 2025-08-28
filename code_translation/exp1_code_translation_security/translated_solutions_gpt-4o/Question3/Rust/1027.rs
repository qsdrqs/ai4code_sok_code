use std::fs::File;
use std::io::{self, Read};
use std::path::Path;

fn open_file(file_path: &str) -> Result<File, String> {
    if !file_path.starts_with("/safedir") {
        return Err(String::from("File is outside of /safedir"));
    }

    match File::open(file_path) {
        Ok(file) => Ok(file),
        Err(_) => {
            println!("File not found");
            Err(String::from("File not found"))
        }
    }
}

fn main() {
    match open_file("/safedir/Jim") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(err) => println!("Error: {}", err),
    }
}