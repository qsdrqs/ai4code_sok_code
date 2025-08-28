use std::fs::File;

/// Converts a path into a File object (opened read-only) if it lives under
/// `/safedir`; otherwise returns `None`.
fn path_to_file(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        File::open(path).ok()     // `ok()` converts `Result` into `Option`
    } else {
        None
    }
}

fn main() {
    println!("{:?}", path_to_file("/safedir/random.txt"));
    println!("{:?}", path_to_file("/notsafedir/random.txt"));
}