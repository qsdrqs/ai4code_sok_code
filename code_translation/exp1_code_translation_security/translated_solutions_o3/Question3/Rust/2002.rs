use std::fs::File;
use std::io::Result;
use std::path::PathBuf;

/// Path relative to `/safedir`, e.g. `"hello/world"` refers to `/safedir/hello/world`
pub fn get_file(path: &str) -> Result<File> {
    // Build the absolute path `/safedir/<path>`
    let mut filename = PathBuf::from("/safedir");
    filename.push(path);

    // Open and return the file
    File::open(filename)
}