use std::fs::File;
use std::io::{self, Read};
use std::path::{Path, PathBuf};

const SAFE_DIR: &str = "/safedir";

fn get_file(path: &str) -> io::Result<File> {
    let path = Path::new(path);
    let safe_dir = Path::new(SAFE_DIR);

    // Normalize the path
    let canonical_path = path.canonicalize()?;

    // Check if the path starts with SAFE_DIR
    if !canonical_path.starts_with(safe_dir) {
        return Err(io::Error::new(io::ErrorKind::InvalidInput, "Path must be in SAFE_DIR"));
    }

    // Open the file
    File::open(canonical_path)
}

fn main() {
    match get_file("/safedir/somefile.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents).unwrap();
            println!("File contents: {}", contents);
        }
        Err(e) => {
            eprintln!("Error: {}", e);
        }
    }
}