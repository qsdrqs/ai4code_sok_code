use std::fs::File;
use std::path::{Path, PathBuf};
use std::io;

fn open_file(filename: &str) -> io::Result<Option<File>> {
    let path = Path::new(filename).canonicalize()?;
    let parts: Vec<&str> = path.iter().map(|os_str| os_str.to_str().unwrap()).collect();

    if parts.len() >= 2 && parts[1] == "safedir" {
        return File::open(&path).map(Some);
    }
    Ok(None)
}

fn test() {
    assert!(open_file("/safedir/../a/b").unwrap().is_none());
    match open_file("/safedir/a") {
        Ok(Some(_file)) => panic!("Expected an error, but file opened successfully"),
        Ok(None) => panic!("Expected an error, but got None"),
        Err(_) => (), // Expected path error
    }
}

fn main() {
    test();
}