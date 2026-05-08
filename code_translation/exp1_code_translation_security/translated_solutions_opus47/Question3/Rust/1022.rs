use std::fs::File;

/// return file at file location
fn get_file(file_location: &str) -> Option<File> {
    if file_location.to_lowercase().trim().contains("/safedir") {
        return File::open(file_location).ok();
    }
    println!("Provided File location is inaccessible");
    None
}

fn main() {
    println!("{:?}", get_file("/safedir/test1.txt"));
}