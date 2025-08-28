use std::fs::{create_dir_all, File};
use std::io::{self, BufRead, BufReader, Write};

/// get_file
///
/// * `file_path` – absolute path to the file
///
/// If `file_path` is **not** inside `/safedir`, the function prints an
/// error-message and returns `None`.  
/// Otherwise it tries to open the file and returns `Some(File)` on success,
/// or prints “File not found” and returns `None` on failure.
fn get_file(file_path: &str) -> Option<File> {
    if !file_path.starts_with("/safedir") {
        println!("File must be inside /safedir");
        return None;
    }

    match File::open(file_path) {
        Ok(file) => Some(file),
        Err(_) => {
            println!("File not found");
            None
        }
    }
}

fn main() -> io::Result<()> {
    // --------------------------------------------------------------------
    // Prepare a test file: /safedir/test.txt containing `"Hello"`
    // --------------------------------------------------------------------
    create_dir_all("/safedir")?;                        // Ensure directory exists
    {
        let mut file = File::create("/safedir/test.txt")?;
        file.write_all(b"Hello")?;                      // Write “Hello”
    }                                                   // file closed here

    // --------------------------------------------------------------------
    // Use get_file on a valid path
    // --------------------------------------------------------------------
    if let Some(mut test_f) = get_file("/safedir/test.txt") {
        let mut reader = BufReader::new(&mut test_f);
        let mut line = String::new();
        reader.read_line(&mut line)?;                   // Read first line
        println!("{}", line);                          // Prints “Hello”
        // `test_f` closed automatically when it goes out of scope
    }

    // --------------------------------------------------------------------
    // Use get_file on an *invalid* path (should fail)
    // --------------------------------------------------------------------
    let test_f = get_file("/test.txt");                // Outside /safedir
    println!("{:?}", test_f);                          // Prints “None”

    Ok(())
}