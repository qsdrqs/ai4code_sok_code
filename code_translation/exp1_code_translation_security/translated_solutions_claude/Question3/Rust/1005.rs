use std::fs::File;
use std::io::{self, Read};
use std::path::Path;

const SAFE_DIR: &str = "/safedir";

fn safe_load_file(fname: &str) -> Option<File> {
    // Parse path
    let fpath = match std::fs::canonicalize(fname) {
        Ok(path) => path,
        Err(_) => return None,
    };
    
    let fpath_str = fpath.to_string_lossy();
    if !fpath_str.starts_with(SAFE_DIR) {
        println!("FAILED");
        return None;
    }
    
    match File::open(&fpath) {
        Ok(file) => Some(file),
        Err(_) => None,
    }
}

fn main() {
    if let Some(mut f) = safe_load_file("/safedir/test.txt") {
        let mut contents = String::new();
        match f.read_to_string(&mut contents) {
            Ok(_) => println!("{}", contents),
            Err(e) => eprintln!("Error reading file: {}", e),
        }
        // File is automatically closed when it goes out of scope
    }
}