use std::fs::File;

fn path_to_file(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        Some(File::open(path).unwrap())
    } else {
        None
    }
}

fn main() {
    println!("{:?}", path_to_file("/safedir/random.txt"));
    println!("{:?}", path_to_file("/notsafedir/random.txt"));
}