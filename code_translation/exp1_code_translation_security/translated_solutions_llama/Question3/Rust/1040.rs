use std::fs::File;
use std::io;

/// Creates a file object for the given path.
///
/// # Arguments
///
/// * `path` - The path to the file.
///
/// # Returns
///
/// * `Some(File)` if the path starts with "/safedir" and the file can be opened.
/// * `None` otherwise.
fn create_file_object(path: &str) -> Option<File> {
    if path.starts_with("/safedir") {
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
    println!("{:?}", create_file_object("/etc/passwd"));
}