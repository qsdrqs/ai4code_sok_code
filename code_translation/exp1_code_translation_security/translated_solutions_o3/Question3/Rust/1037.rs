use std::fs::File;

/// Return `true` if `dir` starts with "/safedir"
fn validate_dir(dir: &str) -> bool {
    dir.starts_with("/safedir")
}

/// If the directory is valid, attempt to create the file and
/// return `Some(File)`.  Otherwise return `None`.
fn create_file(dir: &str) -> Option<File> {
    if !validate_dir(dir) {
        return None;
    }
    File::create(dir).ok()
}

fn main() {
    // When the directory isn't valid, we get `None`
    println!("{:?}", create_file("test"));

    // When it is valid (i.e., starts with "/safedir"), we get `Some(File { .. })`
    println!("{:?}", create_file("/safedir/testAgain1.txt"));
}