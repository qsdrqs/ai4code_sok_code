use std::fs::File;
use std::io::{self, Error, ErrorKind};
use std::path::{Path, PathBuf};

const SAFE: &str = "/safedir/";

fn get_file(path: &str) -> io::Result<File> {
    let safe_path = Path::new(SAFE);
    let test_path = safe_path.join(path).canonicalize()?;

    if !test_path.starts_with(safe_path.canonicalize()?) {
        return Err(Error::new(
            ErrorKind::Other,
            format!("Filename {:?} is not in {:?} directory", test_path, safe_path),
        ));
    }

    File::open(test_path)
}

fn main() {
    match get_file("example.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => eprintln!("Error opening file: {}", e),
    }
}