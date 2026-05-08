use std::error::Error;
use std::ffi::OsStr;
use std::fs::{self, File};
use std::io::Read;
use std::path::{Component, Path};

fn load(path_str: &str) -> Result<File, Box<dyn Error>> {
    // Path::canonicalize is the Rust equivalent of Python's Path.resolve(),
    // it produces an absolute path with all symlinks resolved.
    let path = fs::canonicalize(Path::new(path_str))?;

    // On Unix, components() yields [RootDir, Normal("safedir"), Normal("foo.txt"), ...]
    // so the 2nd component (index 1) corresponds to Python's path.parts[1].
    if path.components().nth(1) != Some(Component::Normal(OsStr::new("safedir"))) {
        return Err("Cannot access unsafe file".into());
    }

    Ok(File::open(&path)?)
}

fn main() -> Result<(), Box<dyn Error>> {
    let mut content = String::new();
    load("/safedir/foo.txt")?.read_to_string(&mut content)?;
    println!("{}", content);

    let mut content = String::new();
    load("/tmp/foo.txt")?.read_to_string(&mut content)?;
    println!("{}", content);

    Ok(())
}