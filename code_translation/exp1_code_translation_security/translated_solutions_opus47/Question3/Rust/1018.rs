use std::fs::File;
use std::io;

fn read_in(path: &str) -> io::Result<Option<File>> {
    if path.contains("/safedir") {
        File::open(path).map(Some)
    } else {
        Ok(None)
    }
}