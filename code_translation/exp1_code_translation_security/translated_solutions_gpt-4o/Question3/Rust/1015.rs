use std::fs::File;
use std::io::Result;

fn get_file_object(input_path: &str) -> Result<File> {
    let first_part = &input_path[0..9];
    if first_part == "/safedir/" {
        return File::open(input_path);
    }
    Err(std::io::Error::new(std::io::ErrorKind::NotFound, "Invalid path"))
}

fn main() {
    match get_file_object("/safedir/test.txt") {
        Ok(file) => {
            // Use the file object as needed
            println!("File opened successfully: {:?}", file);
        }
        Err(e) => {
            println!("Failed to open file: {}", e);
        }
    }
}