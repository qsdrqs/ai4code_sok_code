use std::fs::File;
use std::path::Path;

/// Return an open File handle **only** if the supplied path contains "/safedir"
/// (case-insensitive, with surrounding whitespace ignored).
/// Otherwise, print an error message and return `None`.
fn get_file(file_location: &str) -> Option<File> {
    let trimmed     = file_location.trim();          // strip leading / trailing whitespace
    let lowercase   = trimmed.to_lowercase();        // case-fold equivalent (ASCII only)

    if lowercase.contains("/safedir") {
        match File::open(Path::new(trimmed)) {
            Ok(file) => Some(file),
            Err(err) => {
                eprintln!("Failed to open file: {err}");
                None
            }
        }
    } else {
        println!("Provided File location is inaccessible");
        None
    }
}

fn main() {
    let handle = get_file("/safedir/test1.txt");
    println!("{handle:?}");
}