use std::fs::File;
use std::io::{self, Read};
use std::path::{Path, PathBuf};

fn open_file(path: &str) -> io::Result<File> {
    let safe_dir = Path::new("/safedir").canonicalize()?;
    let file_path = Path::new(path).canonicalize()?;

    if file_path.starts_with(&safe_dir) {
        File::open(file_path)
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Access Denied"))
    }
}

fn main() {
    match open_file("/safedir/test.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents).expect("Failed to read file");
            println!("{}", contents);
        }
        Err(e) => eprintln!("Error: {}", e),
    }
}