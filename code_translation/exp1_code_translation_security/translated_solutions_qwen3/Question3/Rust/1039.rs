use std::fs::File;

fn return_file(path: &str) -> Option<File> {
    if !path.starts_with("/safedir") {
        println!("File cannot be accessed");
        return None;
    }

    match File::open(path) {
        Ok(file) => Some(file),
        Err(_) => None,
    }
}