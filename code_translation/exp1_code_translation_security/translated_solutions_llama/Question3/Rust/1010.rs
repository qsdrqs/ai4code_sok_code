use std::fs::File;
use std::io;
use std::path::Path;

/// Opens a file for writing if the path is within a safe directory.
///
/// # Arguments
///
/// * `filename` - The path to the file to be opened.
///
/// # Returns
///
/// * `Some(File)` if the file can be opened.
/// * `None` if the file path is not within the safe directory or attempts to navigate out of it.
fn open_path(filename: &str) -> Option<File> {
    // Worry about ".." and any way of getting out of directory
    let safe_dir = "/safedir/";
    if !filename.starts_with(safe_dir) {
        return None;
    }
    // Possible weird cases with encodings of ".." I'd have to research
    if filename.contains("/../") {
        return None;
    }
    // Open filename 
    match File::create(filename) {
        Ok(file) => Some(file),
        Err(e) => {
            eprintln!("Error opening file: {}", e);
            None
        }
    }
}

fn main() {
    println!("{:?}", open_path("/safedir/Jim"));
}