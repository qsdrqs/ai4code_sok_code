use std::fs::File;
use std::io;

fn validate_dir(dir: &str) -> bool {
    dir.starts_with("/safedir")
}

fn create_file(dir: &str) -> Option<io::Result<File>> {
    let valid = validate_dir(dir);
    if !valid {
        return None;
    }
    Some(File::create(dir))
}

fn main() {
    println!("{:?}", create_file("test"));
    println!("{:?}", create_file("/safedir/testAgain1.txt"));
}