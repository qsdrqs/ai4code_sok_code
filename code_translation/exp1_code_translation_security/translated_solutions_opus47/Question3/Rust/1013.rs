use std::fs::{self, File};
use std::path::Path;

fn open_file(path: &str) -> Option<File> {
    fn in_safe(fname: &str) -> bool {
        let cwd = Path::new("/safedir");
        // Equivalent to os.path.realpath - resolves symlinks and returns absolute path
        let real_path = match fs::canonicalize(fname) {
            Ok(p) => p,
            Err(_) => return false,
        };
        // Equivalent to os.path.dirname
        let parent = match real_path.parent() {
            Some(p) => p,
            None => return false,
        };
        // Equivalent to os.path.commonpath((path, cwd)) == cwd
        // Path::starts_with does component-wise comparison, so "/safedir_other"
        // will NOT start with "/safedir"
        parent.starts_with(cwd)
    }

    if in_safe(path) {
        File::open(path).ok()
    } else {
        None
    }
}

fn main() {
    let file = open_file("/env.sh");
    match file {
        Some(f) => println!("{:?}", f),
        None => println!("None"),
    }
}