use std::{
    fs::{canonicalize, File},
    io::{Read, Result},
    path::{Path, PathBuf},
};

const SAFE_DIR: &str = "/safedir";

/// Attempts to open `fname` only if it resolves inside `SAFE_DIR`.
/// Returns `Some(File)` on success, otherwise prints `"FAILED"` and returns `None`.
fn safe_load_file(fname: &str) -> Option<File> {
    // Resolve the absolute, canonical path (i.e. follow symlinks).
    let fpath: PathBuf = match canonicalize(fname) {
        Ok(p) => p,
        Err(_) => {
            println!("FAILED");
            return None;
        }
    };

    // Check that the resolved path is inside SAFE_DIR.
    if !fpath.starts_with(Path::new(SAFE_DIR)) {
        println!("FAILED");
        return None;
    }

    // Open the file for reading.
    File::open(&fpath).ok()
}

fn main() -> Result<()> {
    if let Some(mut file) = safe_load_file("/safedir/test.txt") {
        let mut contents = String::new();
        file.read_to_string(&mut contents)?;
        println!("{}", contents);
        // `file` is automatically closed when it goes out of scope.
    }
    Ok(())
}