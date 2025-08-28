use std::fs::File;
use std::io;
use std::path::Path;

// Function to open a file and check if it's within /safedir
fn open_file(file_path: &str) -> Option<File> {
    // Check if file path starts with /safedir
    if !file_path.starts_with("/safedir") {
        panic!("File is outside of /safedir");
    }

    // Attempt to open the file
    match File::open(file_path) {
        Ok(file) => Some(file),
        Err(err) => {
            eprintln!("File not found: {}", err);
            None
        }
    }
}

fn main() {
    let path = open_file("/safedir/Jim");
    println!("{:?}", path);
}