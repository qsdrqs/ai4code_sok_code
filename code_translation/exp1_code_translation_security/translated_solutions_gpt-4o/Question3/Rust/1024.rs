use std::fs::File;
use std::io::{self, Write};
use std::path::{Path, PathBuf};

fn return_object(path: &str) -> Result<File, String> {
    let rpath = match std::fs::canonicalize(path) {
        Ok(p) => p,
        Err(e) => return Err(format!("Error: {}", e)),
    };

    if rpath.to_str().unwrap_or("").starts_with("/safedir") {
        return Err(String::from("-1"));
    }

    match File::create(path) {
        Ok(fp) => Ok(fp),
        Err(e) => Err(format!("Error: {}", e)),
    }
}

fn main() {
    let file_name = "/tmp/foo";
    match return_object(file_name) {
        Ok(mut file) => {
            println!("File created successfully: {:?}", file);
            // You can write to the file if needed
            // writeln!(file, "Some content").unwrap();
        }
        Err(e) => println!("{}", e),
    }
}