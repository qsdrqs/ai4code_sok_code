use std::fs::File;
use std::io;
use std::path::Path;

fn open_file(path: &str) -> io::Result<File> {
    if let Some((_, tail)) = path.split_once("/safedir") {
        let new_path = format!("/safedir{}", tail);
        File::open(Path::new(&new_path))
    } else {
        Err(io::Error::new(io::ErrorKind::InvalidInput, "Path does not contain '/safedir'"))
    }
}