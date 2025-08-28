use std::fs::File;
use std::io;
use std::path::Path;

/// Checks if a given string represents a safe directory path.
///
/// A path is considered safe if it starts with "/safedir" and does not contain "/..".
fn check_if_safe(my_string: &str) -> bool {
    my_string.starts_with("/safedir") && !my_string.contains("/..")
}

/// Attempts to open a file at the given filepath if it's considered safe.
///
/// Returns a `File` object if the filepath is safe and the file can be opened, `None` otherwise.
fn get_file(filepath: &str) -> Option<File> {
    if check_if_safe(filepath) {
        match File::open(filepath) {
            Ok(file) => Some(file),
            Err(_) => None,
        }
    } else {
        None
    }
}

fn main() -> io::Result<()> {
    let filepath = "/safedir/safe_file.txt";
    match get_file(filepath) {
        Some(file) => {
            println!("File opened successfully: {}", filepath);
            Ok(())
        }
        None => {
            println!("Failed to open file or filepath is not safe: {}", filepath);
            Ok(())
        }
    }
}