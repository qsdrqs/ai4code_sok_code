use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

/// Return `Ok(true)` if `fname` lives **inside** `/safedir`, otherwise `Ok(false)`.
/// Any IO error that occurs while resolving the path is forwarded to the caller.
fn in_safe(fname: &str) -> io::Result<bool> {
    // “Safe” directory – the place files are allowed to come from
    let safe_dir = Path::new("/safedir").canonicalize()?;

    // Absolute, canonical directory that contains the candidate file
    let file_dir: PathBuf = Path::new(fname)
        .canonicalize()?               // → realpath(..) in Python
        .parent()                      // take dirname(..)
        .map(Path::to_path_buf)
        .unwrap_or_else(|| PathBuf::from("/"));

    Ok(file_dir.starts_with(&safe_dir))
}

/// Tries to open the file in read-only mode if it is inside `/safedir`.
/// Returns `Some(File)` on success and `None` otherwise.
fn open_file(path: &str) -> Option<File> {
    match in_safe(path) {
        Ok(true) => File::open(path).ok(),
        _ => None,
    }
}

fn main() {
    // Python printed the file object or None; we do something similar
    match open_file("/env.sh") {
        Some(_) => println!("Some(File)"),
        None => println!("None"),
    }
}