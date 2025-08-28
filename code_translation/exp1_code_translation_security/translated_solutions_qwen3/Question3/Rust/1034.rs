use std::fs::File;
use std::process;

/// Checks if the path starts with "/safedir".
/// This function will panic if the path is shorter than 8 characters,
/// just like the original Python code would raise an IndexError.
fn check_dir(path: &str) -> bool {
    let bytes = path.as_bytes();
    bytes[0] == b'/' &&
    bytes[1] == b's' &&
    bytes[2] == b'a' &&
    bytes[3] == b'f' &&
    bytes[4] == b'e' &&
    bytes[5] == b'd' &&
    bytes[6] == b'i' &&
    bytes[7] == b'r'
}

/// Attempts to open the file at the given path.
/// - If the path is not valid (i.e., not starting with "/safedir"), exits with code 0.
/// - If the file cannot be opened, prints "Error" and exits with code 0.
/// - Otherwise, returns the opened file.
fn get_file_object(path: &str) -> File {
    if check_dir(path) {
        match File::open(path) {
            Ok(file) => file,
            Err(_) => {
                println!("Error");
                process::exit(0);
            }
        }
    } else {
        process::exit(0);
    }
}

fn main() {
    let _file = get_file_object("/safedir/test1.txt");
}