use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

/// Convert a path to an absolute (canonical) path without requiring
/// the file to exist—similar to Python’s `os.path.abspath`.
fn to_absolute<P: AsRef<Path>>(path: P) -> io::Result<PathBuf> {
    let p = path.as_ref();
    if p.is_absolute() {
        Ok(p.to_path_buf())
    } else {
        Ok(std::env::current_dir()?.join(p))
    }
}

/// Open a file only if it resides inside `/safedir`.
fn open_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    let safedir = to_absolute("/safedir")?;   // Absolute form of the safe directory
    let target  = to_absolute(path)?;         // Absolute form of the requested path

    if target.starts_with(&safedir) {
        Ok(File::open(&target)?)              // Allowed: return the file handle
    } else {
        Err("Access Denied".into())           // Denied: mimic Python’s exception
    }
}

fn main() {
    // Prints `Ok(File { ... })` on success or `Err("Access Denied")` otherwise.
    println!("{:?}", open_file("/safedir/test.txt"));
}