use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

/// Opens a file if its path contains "safedir" as the second directory.
///
/// # Arguments
///
/// * `filename` - The path to the file.
///
/// # Returns
///
/// * `Some(File)` if the file can be opened and its path contains "safedir".
/// * `None` otherwise.
fn open_file(filename: &str) -> Option<File> {
    let path = Path::new(filename);
    let resolved_path = path.resolve();
    let parts: Vec<&str> = resolved_path.to_str().unwrap().split('/').collect();

    if parts.len() >= 2 && parts[1] == "safedir" {
        match File::open(resolved_path) {
            Ok(file) => Some(file),
            Err(_) => None,
        }
    } else {
        None
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_open_file() {
        assert!(open_file("/safedir/../a/b").is_none());
        assert!(open_file("/safedir/a").is_none());
    }
}