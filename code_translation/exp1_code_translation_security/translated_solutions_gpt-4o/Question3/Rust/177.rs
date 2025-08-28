use std::fs::File;
use std::io::Result;
use std::path::{Path, PathBuf};

fn path2obj(file_path: &str) -> Option<File> {
    let mut directory = Path::new(file_path).parent();

    while let Some(dir) = directory {
        if let Some(base_name) = dir.file_name() {
            if base_name == "safedir" {
                return File::open(file_path).ok();
            }
        }
        directory = dir.parent();
    }

    None
}