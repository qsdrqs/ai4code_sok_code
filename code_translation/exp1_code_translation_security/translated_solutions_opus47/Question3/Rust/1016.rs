use std::fs::File;

fn path_to_file(path: &str) -> Option<File> {
    // converts the path to a file object
    if path.contains("/safedir") {
        Some(File::open(path).expect("Failed to open file"))
    } else {
        None
    }
}

fn main() {
    println!("{:?}", path_to_file("/safedir/random.txt"));
    println!("{:?}", path_to_file("/notsafedir/random.txt"));
}