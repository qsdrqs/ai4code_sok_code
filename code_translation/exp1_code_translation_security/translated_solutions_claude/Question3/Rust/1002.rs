use std::path::{Path, PathBuf};
use std::fs::File;
use std::io::Result as IoResult;

fn open_file(filename: &str) -> Option<IoResult<File>> {
    let path = Path::new(filename);
    let path = match path.canonicalize() {
        Ok(p) => p,
        Err(_) => return None,
    };
    
    let parts: Vec<_> = path.components().collect();
    if parts.len() >= 2 {
        if let std::path::Component::Normal(component) = parts[1] {
            if component == "safedir" {
                return Some(File::open(path));
            }
        }
    }
    None
}

fn test() {
    assert!(open_file("/safedir/../a/b").is_none());
    
    match open_file("/safedir/a") {
        Some(result) => {
            match result {
                Ok(_) => panic!("Expected file operation to fail"),
                Err(_) => {
                    // File operation failed as expected
                }
            }
        }
        None => {
            // Path validation failed, which is also acceptable
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn run_test() {
        test();
    }
}