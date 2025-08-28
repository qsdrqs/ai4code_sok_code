use std::fs::File;
use std::io;

fn return_file(path: &str) -> Option<File> {
    if !path.starts_with("/safedir") {
        eprintln!("File cannot be accessed");
        return None;
    }

    match File::open(path) {
        Ok(file) => Some(file),
        Err(err) => {
            eprintln!("Error opening file: {}", err);
            None
        }
    }
}

fn main() {
    let path = "/safedir/test.txt";
    match return_file(path) {
        Some(file) => println!("File opened successfully"),
        None => println!("File could not be opened"),
    }
}