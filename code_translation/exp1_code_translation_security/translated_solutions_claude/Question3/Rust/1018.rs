use std::fs::File;
use std::io::Result;

fn read_in(path: &str) -> Option<Result<File>> {
    if path.contains("/safedir") {
        Some(File::open(path))
    } else {
        None
    }
}