// Cargo.toml dependencies:
// [dependencies]
// path-clean = "1.0"

use std::path::{Path, PathBuf, Component};
use std::fs::File;
use std::io;
use path_clean::PathClean;

fn open_file(filename: &str) -> io::Result<Option<File>> {
    let path = Path::new(filename);
    let cleaned: PathBuf = path.clean();
    let components: Vec<Component> = cleaned.components().collect();

    if components.len() >= 2 {
        if let Component::Normal(name) = components[1] {
            if name == "safedir" {
                return File::open(&cleaned).map(Some);
            }
        }
    }
    Ok(None)
}

fn test() {
    assert!(open_file("/safedir/../a/b").unwrap().is_none());
    assert!(open_file("/safedir/a").is_err());
}

fn main() {
    test();
}