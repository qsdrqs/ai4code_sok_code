use std::fs::File;

fn check_if_safe(mystring: &str) -> bool {
    mystring.starts_with("/safedir") && !mystring.contains("/..")
}

fn get_file(filepath: &str) -> Option<File> {
    if check_if_safe(filepath) {
        File::open(filepath).ok()
    } else {
        None
    }
}