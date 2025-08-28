use std::fs::File;
use std::io::{self, Write, BufReader, BufRead};
use std::path::Path;

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

fn main() -> io::Result<()> {
    // Create and write to the file
    let mut file = File::create("/safedir/test.txt")?;
    file.write_all(b"Hello")?;

    // Read from the file
    if let Some(file) = get_file("/safedir/test.txt") {
        let mut reader = BufReader::new(file);
        let mut line = String::new();
        if reader.read_line(&mut line).is_ok() {
            print!("{}", line);
        }
    }

    // Attempt to get a file outside of /safedir
    let test_f = get_file("/test.txt"); // Should fail
    println!("{:?}", test_f);

    Ok(())
}