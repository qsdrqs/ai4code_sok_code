use std::fs::File;
use std::path::Path;

fn get_file_path(path: &str) -> Option<File> {
    /*
    Function that takes as input a string path representing a file path
    */
    let file_path = Path::new(path);
    if file_path.is_file() {
        File::open(file_path).ok()
    } else {
        None
    }
}