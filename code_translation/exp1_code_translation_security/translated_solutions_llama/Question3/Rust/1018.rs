use std::fs::File;
use std::io;

pub fn read_in(path: &str) -> Option<File> {
    if path.contains("/safedir") {
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
    let path_with_safedir = "/path/to/safedir/file.txt";
    let path_without_safedir = "/path/to/other/file.txt";

    match read_in(path_with_safedir) {
        Some(file) => println!("File opened successfully"),
        None => println!("File not opened"),
    }

    match read_in(path_without_safedir) {
        Some(_) => println!("This should not be printed"),
        None => println!("File not opened as expected"),
    }
}