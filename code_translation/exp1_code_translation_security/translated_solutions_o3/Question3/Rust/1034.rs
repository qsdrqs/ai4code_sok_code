use std::fs::File;
use std::process;

/// Returns `true` if the supplied path starts with “/safedir”.
fn check_dir(path: &str) -> bool {
    path.as_bytes().starts_with(b"/safedir")
}

/// Attempts to open `path` for reading.  
/// If the path does **not** start with “/safedir”, or the file cannot be opened,
/// the process terminates (exit-code 1), mirroring the Python behaviour.
fn get_file_object(path: &str) -> File {
    if check_dir(path) {
        match File::open(path) {
            Ok(f)  => f,
            Err(_) => {
                println!("Error");
                process::exit(1);
            }
        }
    } else {
        process::exit(1);
    }
}

fn main() {
    let file = get_file_object("/safedir/test1.txt");
    // `std::fs::File` implements `Debug`, so this prints a representation
    // similar in spirit to Python’s default file object printout.
    println!("{:?}", file);
}