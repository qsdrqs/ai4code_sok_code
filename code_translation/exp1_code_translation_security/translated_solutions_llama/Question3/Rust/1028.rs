use std::fs::File;
use std::io::{Read, Write};

/// Opens a file in read mode.
///
/// # Arguments
///
/// * `file_path` - The path to the file to be opened.
///
/// # Returns
///
/// A `Result` containing a `File` object if successful, or an `io::Error` if not.
fn open_file(file_path: &str) -> Result<File, std::io::Error> {
    File::open(file_path)
}

fn main() {
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Open the file in write mode and write to it
    let mut file = File::create(file_path).expect("Failed to create file");
    file.write_all(b"Hello it's Melo").expect("Failed to write to file");

    // Open the file in read mode and read from it
    let mut file = open_file(file_path).expect("Failed to open file");
    let mut content = String::new();
    file.read_to_string(&mut content).expect("Failed to read from file");
    println!("{}", content);
}