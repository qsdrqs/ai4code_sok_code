use std::fs::File;
use std::io;
use std::path::{Path, PathBuf};

fn in_safe_dir(fname: &Path, safe_dir: &Path) -> bool {
    let path = fname.canonicalize().unwrap().parent().unwrap();
    let safe_dir = safe_dir.canonicalize().unwrap();
    path.starts_with(safe_dir)
}

fn open_file(path: &Path, safe_dir: &Path) -> io::Result<Option<File>> {
    if in_safe_dir(path, safe_dir) {
        File::open(path).map(Some)
    } else {
        Ok(None)
    }
}

fn main() -> io::Result<()> {
    let safe_dir = Path::new("/safedir");
    let file_path = Path::new("/env.sh");
    match open_file(file_path, safe_dir) {
        Ok(Some(file)) => {
            println!("File opened successfully: {:?}", file);
        }
        Ok(None) => {
            println!("File is not in the safe directory.");
        }
        Err(err) => {
            eprintln!("Error opening file: {}", err);
        }
    }
    Ok(())
}