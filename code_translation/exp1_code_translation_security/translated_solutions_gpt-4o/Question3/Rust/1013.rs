use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

fn open_file(path: &str) -> io::Result<Option<File>> {
    fn in_safe(fname: &str) -> bool {
        let cwd = Path::new("/safedir").canonicalize().unwrap();
        let path = Path::new(fname).canonicalize().unwrap();
        path.starts_with(&cwd)
    }

    if in_safe(path) {
        let file = File::open(path)?;
        Ok(Some(file))
    } else {
        Ok(None)
    }
}

fn main() {
    match open_file("/env.sh") {
        Ok(Some(file)) => {
            println!("File opened successfully: {:?}", file);
        }
        Ok(None) => {
            println!("File is not in a safe directory.");
        }
        Err(e) => {
            println!("Error opening file: {}", e);
        }
    }
}