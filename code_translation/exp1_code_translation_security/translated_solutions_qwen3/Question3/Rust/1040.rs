use std::fs::File;

fn create_file_object(path: &str) -> Option<File> {
    if path.starts_with("/safedir") {
        Some(File::open(path).unwrap())
    } else {
        None
    }
}

fn main() {
    println!("{:?}", create_file_object("/etc/passwd"));
}