use std::fs::File;
use std::io::Result as IoResult;

fn check_if_safe(mystring: &str) -> bool {
    mystring.starts_with("/safedir") && !mystring.contains("/..")
}

fn get_file(filepath: &str) -> Option<IoResult<File>> {
    if check_if_safe(filepath) {
        Some(File::open(filepath))
    } else {
        None
    }
}