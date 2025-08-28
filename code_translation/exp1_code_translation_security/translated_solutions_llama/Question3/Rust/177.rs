use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

/// Opens a file and checks if its directory path contains a directory named "safedir".
///
/// # Arguments
///
/// * `file_path` - The path to the file to be opened.
///
/// # Returns
///
/// * `Some(File)` if the directory path contains "safedir", `None` otherwise.
fn path2obj(file_path: &str) -> Option<File> {
    let file_path = Path::new(file_path);
    let file = match File::open(file_path) {
        Ok(file) => file,
        Err(e) => {
            eprintln!("Error opening file: {}", e);
            return None;
        }
    };

    let mut directory = file_path.parent();
    while let Some(dir) = directory {
        if dir.file_name().and_then(|name| name.to_str()) == Some("safedir") {
            return Some(file);
        }
        directory = dir.parent();
    }
    None
}

fn main() -> io::Result<()> {
    let file_path = "path_to_your_file.txt"; // replace with your file path
    if let Some(file) = path2obj(file_path) {
        println!("File opened successfully.");
    } else {
        println!("File not opened.");
    }
    Ok(())
}