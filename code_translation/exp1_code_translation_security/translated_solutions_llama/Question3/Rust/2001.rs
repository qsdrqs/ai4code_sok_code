use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

const SAFE_DIR: &str = "/safedir";

/// Opens a file for reading if its path is within the SAFE_DIR.
///
/// # Arguments
///
/// * `path` - The path to the file to be opened.
///
/// # Returns
///
/// A `Result` containing a `File` object if successful, or an `io::Error` if not.
///
/// # Panics
///
/// If the path is not within SAFE_DIR, a `ValueError` equivalent is returned as an `io::Error`.
fn get_file(path: &str) -> io::Result<File> {
    let path = Path::new(path).canonicalize()?;
    if !path.starts_with(SAFE_DIR) {
        return Err(io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path must be in SAFE_DIR",
        ));
    }
    File::open(path)
}

fn main() -> io::Result<()> {
    let file_path = "/safedir/example.txt";
    match get_file(file_path) {
        Ok(file) => {
            println!("File opened successfully: {:?}", file.metadata()?);
        }
        Err(err) => {
            eprintln!("Error opening file: {}", err);
        }
    }
    Ok(())
}