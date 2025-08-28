use std::fs::File;

/// Opens a file if it's within the `/safedir` directory.
/// Returns `Some(File)` if successful, `None` if the file is not found,
/// and panics if the file is outside `/safedir`.
fn open_file(file_path: &str) -> Option<File> {
    if !file_path.starts_with("/safedir") {
        panic!("File is outside of /safedir");
    }

    match File::open(file_path) {
        Ok(file) => Some(file),
        Err(_) => {
            println!("File not found");
            None
        }
    }
}

fn main() {
    let path = open_file("/safedir/Jim");
    println!("{:?}", path);
}