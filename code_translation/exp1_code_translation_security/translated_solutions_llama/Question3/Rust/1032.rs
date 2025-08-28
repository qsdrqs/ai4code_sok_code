use std::fs::File;
use std::io::{Read, Error};

/// Opens a file if its path starts with `/safedir`.
///
/// # Arguments
///
/// * `path` - The path to the file.
///
/// # Returns
///
/// A `Result` containing a `File` if successful, or an `Error` if not.
///
/// # Panics
///
/// If the file cannot be read.
fn get_file(path: &str) -> Result<File, Error> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err(Error::new(std::io::ErrorKind::PermissionDenied, "Access denied"))
    }
}

fn main() -> std::io::Result<()> {
    let file_path = "/safedir/test1.txt";
    match get_file(file_path) {
        Ok(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents)?;
            println!("{}", contents);
        },
        Err(e) => {
            eprintln!("Error: {}", e);
        }
    }
    // Uncomment the line below to test access denied
    // let file_path = "/etc/passwd";
    // match get_file(file_path) {
    //     Ok(_) => println!("File opened successfully"),
    //     Err(e) => eprintln!("Error: {}", e),
    // }
    Ok(())
}