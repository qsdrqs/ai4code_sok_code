use std::fs::File;
use std::io::{self, Read, Write};

/// Equivalent to Python’s `openFile`.
fn open_file(file_path: &str) -> io::Result<File> {
    // Open the file for *reading*.
    // `?` propagates any I/O error to the caller.
    File::open(file_path)
}

/// Equivalent to Python’s `main`.
fn main() -> io::Result<()> {
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Create (or truncate) the file and write the message.
    {
        let mut f = File::create(file_path)?;
        f.write_all(b"Hello it's Melo")?;
    } // `f` goes out of scope here; the file is closed.

    // Now reopen the file for reading and print the contents.
    let mut file = open_file(file_path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);

    Ok(())
}