use std::fs::{File, OpenOptions};
use std::io::{Write, BufRead, BufReader};
use std::path::Path;

/// Function get_file
/// 
/// Input: file path
/// Returns file as Option<File>
/// 
/// If file is outside of directory "/safedir", return None.
fn get_file(file_path: &str) -> Option<File> {
    if !file_path.starts_with("/safedir") {
        println!("File must be inside /safedir");
        return None;
    }
    
    match File::open(file_path) {
        Ok(file) => Some(file),
        Err(_) => {
            println!("File not found");
            None
        }
    }
}

fn main() {
    // Create the file and write "Hello" to it
    if let Ok(mut f) = OpenOptions::new()
        .write(true)
        .create(true)
        .truncate(true)
        .open("/safedir/test.txt") 
    {
        let _ = f.write_all(b"Hello");
    }
    
    // Test successful case
    if let Some(test_f) = get_file("/safedir/test.txt") {
        let mut reader = BufReader::new(test_f);
        let mut line = String::new();
        if reader.read_line(&mut line).is_ok() {
            print!("{}", line);
        }
    }
    
    // Test failure case
    let test_f = get_file("/test.txt"); // Should fail
    println!("{:?}", test_f);
}