use std::fs::File;
use std::io;
use std::path::Path;

fn get_file(path: &str) -> io::Result<File> {
    let filename = format!("/safedir/{}", path);
    File::open(Path::new(&filename))
}