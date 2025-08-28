use std::fs::File;
use std::path::Path;

/// Behaviour equivalent to the original Python `thing` function.
///
/// Returns `Some(File)` when the path contains `/safedir` **and** can be opened.
/// Otherwise prints an error message and returns `None`.
fn thing<P: AsRef<Path>>(path: P) -> Option<File> {
    let path_ref = path.as_ref();
    let path_str = path_ref.to_string_lossy();

    if path_str.contains("/safedir") {
        match File::open(path_ref) {
            Ok(file) => Some(file),
            Err(err) => {
                eprintln!("Error opening file: {err}");
                None
            }
        }
    } else {
        println!("invalid path");
        None
    }
}

fn main() {
    // Test good path
    let f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    println!("\nResult 1:");
    println!("{:?}", f);

    // Test bad path
    let f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    println!("\nResult 2:");
    println!("{:?}", f);
}