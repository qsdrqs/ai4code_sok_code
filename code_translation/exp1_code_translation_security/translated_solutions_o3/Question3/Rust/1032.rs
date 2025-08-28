use std::fs::File;
use std::io::{Read, Result};

/// Opens a file only if it is located under `/safedir`.
/// Otherwise the function aborts execution, just like the
/// Python example raised an exception.
fn get_file(path: &str) -> File {
    if path.starts_with("/safedir") {
        File::open(path).expect("failed to open file")
    } else {
        panic!("Access denied");
    }
}

fn main() -> Result<()> {
    // Same call as in the original Python snippet
    let mut file = get_file("/safedir/test1.txt");
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);

    // Uncomment to reproduce the “Access denied” panic
    // let mut file = get_file("/etc/passwd");
    // let mut contents = String::new();
    // file.read_to_string(&mut contents)?;
    // println!("{}", contents);

    Ok(())
}