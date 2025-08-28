use std::fs::File;
use std::path::{Path, PathBuf};
use std::io::Result as IoResult;

fn open_file(path: &str) -> Option<File> {
    fn in_safe(fname: &str) -> bool {
        let cwd = PathBuf::from("/safedir");
        let cwd = cwd.canonicalize().unwrap_or(cwd);
        
        let path = Path::new(fname);
        let path = match path.canonicalize() {
            Ok(p) => p,
            Err(_) => return false,
        };
        
        let parent = match path.parent() {
            Some(p) => p,
            None => return false,
        };
        
        parent.starts_with(&cwd)
    }
    
    if in_safe(path) {
        match File::open(path) {
            Ok(file) => Some(file),
            Err(_) => None,
        }
    } else {
        None
    }
}

fn main() {
    match open_file("/env.sh") {
        Some(_file) => println!("File opened successfully"),
        None => println!("None"),
    }
}