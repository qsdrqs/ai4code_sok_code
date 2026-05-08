use std::fs::{self, File};
use std::io::Read;

const SAFE_DIR: &str = "/safedir";

fn safe_load_file(fname: &str) -> Option<File> {
    // Parse path - canonicalize resolves symlinks like Python's realpath
    let fpath = match fs::canonicalize(fname) {
        Ok(p) => p,
        Err(_) => {
            println!("FAILED");
            return None;
        }
    };

    let fpath_str = fpath.to_string_lossy();

    if !fpath_str.starts_with(SAFE_DIR) {
        println!("FAILED");
        return None;
    }

    File::open(&fpath).ok()
}

fn main() {
    let mut f = safe_load_file("/safedir/test.txt").expect("Failed to load file");
    let mut contents = String::new();
    f.read_to_string(&mut contents).expect("Failed to read file");
    println!("{}", contents);
    // File is automatically closed when `f` goes out of scope (Drop)
}