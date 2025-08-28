use std::fs::File;
use std::io::Result;
use std::path::Path;

// Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
fn get_file(path: &str) -> Result<File> {
    let filename = format!("/safedir/{}", path);
    File::open(&filename)
}