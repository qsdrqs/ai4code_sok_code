use std::fs::File;
use std::path::Path;

fn get_file_path(path: &str) -> Option<File> {
    /**
     * function that takes as input a string path representing a file path
     */
    if Path::new(path).is_file() {
        match File::open(path) {
            Ok(file) => Some(file),
            Err(_) => None,
        }
    } else {
        None
    }
}