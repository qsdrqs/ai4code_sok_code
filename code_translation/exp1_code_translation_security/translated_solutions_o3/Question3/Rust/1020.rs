use std::fs::File;
use std::path::Path;

/// Tries to open `path` if it lives under "/safedir".
/// On success returns an opened `File`; otherwise an error message.
fn func_path<P: AsRef<Path>>(path: P) -> Result<File, String> {
    let path_ref = path.as_ref();
    let path_str = path_ref.to_string_lossy();

    // check that "/safedir" is part of the path
    if path_str.contains("/safedir") {
        // attempt to open the file, convert I/O errors to String
        File::open(path_ref).map_err(|e| format!("unable to open file: {}", e))
    } else {
        Err(String::from("file not found or unsafe for access."))
    }
}

fn main() {
    match func_path("/safedir/Jim") {
        Ok(file) => println!("opened file successfully: {:?}", file),
        Err(msg)  => println!("{}", msg),
    }
}