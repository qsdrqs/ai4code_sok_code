use std::fs::File;
use std::io::{self, Read};
use std::path::{Path, PathBuf};

const SAFE_DIR: &str = "/safedir";

fn safe_load_file(fname: &str) -> Option<File> {
    // Parse path
    let fpath = match std::fs::canonicalize(fname) {
        Ok(p) => p,
        Err(_) => {
            println!("FAILED");
            return None;
        }
    };

    if !fpath.starts_with(SAFE_DIR) {
        println!("FAILED");
        return None;
    }

    match File::open(&fpath) {
        Ok(file) => Some(file),
        Err(_) => {
            println!("FAILED");
            None
        }
    }
}

fn main() -> io::Result<()> {
    if let Some(mut f) = safe_load_file("/safedir/test.txt") {
        let mut contents = String::new();
        f.read_to_string(&mut contents)?;
        println!("{}", contents);
    }
    Ok(())
}