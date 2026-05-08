use std::fs::File;
use std::path::Path;

/// function that takes as input a string path representing a file path
fn get_file_path(path: &str) -> Option<File> {
    if Path::new(path).is_file() {
        File::open(path).ok()
    } else {
        None
    }
}