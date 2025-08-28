use std::fs::File;
use std::io::Result;
use std::path::Path;

fn open_file(path: &str) -> Result<File> {
    let separator = "/safedir";
    
    if let Some(pos) = path.find(separator) {
        let newpath = &path[pos..];
        File::open(newpath)
    } else {
        // If "/safedir" is not found, use empty string + empty string (equivalent to Python's behavior)
        File::open("")
    }
}