use std::fs::File;
use std::io;
use std::path::Path;

fn path2obj(file_path: &str) -> io::Result<Option<File>> {
    let path = Path::new(file_path);
    let f = File::open(path)?;
    let mut directory = path.parent();

    while let Some(dir) = directory {
        if let Some(name) = dir.file_name() {
            if name == "safedir" {
                return Ok(Some(f));
            }
        }
        directory = dir.parent();
    }

    Ok(None)
}