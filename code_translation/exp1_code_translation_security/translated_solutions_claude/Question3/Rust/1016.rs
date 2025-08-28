use std::fs::File;
use std::io::Result;

/// Converts the path to a file object
fn path_to_file(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        match File::open(path) {
            Ok(file) => Some(file),
            Err(_) => None,
        }
    } else {
        None
    }
}

fn main() {
    println!("{:?}", path_to_file("/safedir/random.txt"));
    println!("{:?}", path_to_file("/notsafedir/random.txt"));
}