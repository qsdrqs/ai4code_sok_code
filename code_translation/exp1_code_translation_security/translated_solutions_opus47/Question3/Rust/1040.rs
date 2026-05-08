use std::fs::File;

/// create file object for path
fn create_file_object(path: &str) -> Option<File> {
    if path.starts_with("/safedir") {
        File::open(path).ok()
    } else {
        None
    }
}

fn main() {
    println!("{:?}", create_file_object("/etc/passwd"));
}