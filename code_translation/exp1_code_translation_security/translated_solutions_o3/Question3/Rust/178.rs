use std::fs::File;
use std::io::Read; // Only needed below for the small demo
                   // (It’s fine to omit if you don’t want to read the file)

const ALLOWED_PATH: &str = "/safedir";

/// Attempts to open a file only if the path starts with `/safedir`.
/// On success you get a File handle, on failure you get a message.
fn get_file(path: &str) -> Result<File, &'static str> {
    if !path.starts_with(ALLOWED_PATH) {
        return Err("Not allowed to get file from this dir");
    }

    // Propagate any I/O error as a simple string
    File::open(path).map_err(|_| "Failed to open file")
}

fn main() {
    match get_file("/safedir/test/path") {
        Ok(mut file) => {
            // --- Demo: read the file into a string and print it -------------
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                eprintln!("Unable to read file: {e}");
            } else {
                println!("File contents:\n{contents}");
            }
            // ----------------------------------------------------------------
        }
        Err(msg) => println!("{msg}"),
    }
}