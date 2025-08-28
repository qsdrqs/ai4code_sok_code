use std::fs::File;

fn validate_dir(dir: &str) -> bool {
    dir.starts_with("/safedir")
}

fn create_file(dir: &str) -> Option<File> {
    if !validate_dir(dir) {
        return None;
    }
    Some(File::create(dir).unwrap())
}

fn main() {
    println!("{:?}", create_file("test"));
    println!("{:?}", create_file("/safedir/testAgain1.txt"));
}