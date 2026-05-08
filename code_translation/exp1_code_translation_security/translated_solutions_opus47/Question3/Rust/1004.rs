use std::fs::{File, OpenOptions};
use std::io::{BufRead, BufReader, Write};

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
    // Equivalent to Python's: with open("/safedir/test.txt", "w") as f: f.write("Hello")
    let mut f = OpenOptions::new()
        .write(true)
        .create(true)
        .truncate(true)
        .open("/safedir/test.txt")
        .expect("Could not create file");
    f.write_all(b"Hello").expect("Could not write to file");
    drop(f); // Close the file (end of `with` block)

    // Get and read from the file
    let test_f = get_file("/safedir/test.txt").unwrap();
    let mut reader = BufReader::new(test_f);
    let mut line = String::new();
    reader.read_line(&mut line).expect("Could not read line");
    println!("{}", line);
    // File is automatically closed when `reader` goes out of scope (RAII)

    // Should fail (outside /safedir)
    let test_f = get_file("/test.txt");
    println!("{:?}", test_f);
}