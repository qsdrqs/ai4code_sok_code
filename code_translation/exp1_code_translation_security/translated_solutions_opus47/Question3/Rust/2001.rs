use std::fs::File;
use std::io;
use std::path::PathBuf;
use path_clean::PathClean;

const SAFE_DIR: &str = "/safedir";

fn get_file(path: &str) -> io::Result<File> {
    let normalized = PathBuf::from(path).clean();

    if !normalized.starts_with(SAFE_DIR) {
        return Err(io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path must be in SAFE_DIR",
        ));
    }

    File::open(path)
}