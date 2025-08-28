use std::fs::File;
use std::io::{Read, BufReader};
use std::path::Path;

const SAFE_DIR: &str = "/safedir";

fn safe_load_file(fname: &str) -> Option<BufReader<File>> {
    // Parse path
    let fpath = std::path::Path::new(fname).canonicalize().ok()?;
    if !fpath.to_str().unwrap().starts_with(SAFE_DIR) {
        eprintln!("FAILED");
        return None;
    }
    let file = File::open(fpath).ok()?;
    Some(BufReader::new(file))
}

fn main() {
    if let Some(mut f) = safe_load_file("/safedir/test.txt") {
        let mut contents = String::new();
        f.read_to_string(&mut contents).expect("Failed to read file");
        println!("{}", contents);
    }
}