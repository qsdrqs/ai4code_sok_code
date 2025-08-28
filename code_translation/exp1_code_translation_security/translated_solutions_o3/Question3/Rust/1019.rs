use std::fs::{File, OpenOptions};
use std::io::{Read};
use std::path::{Path, PathBuf};

use path_clean::PathClean;   // brings `.clean()` into scope

/// Rough equivalent of Python’s os.path.normpath + policy check.
fn get_file(path: &str) -> File {
    // 1.  Normalise the path (collapse “.”, “..”, duplicate slashes, …)
    let cleaned: PathBuf = Path::new(path).clean();
    let cleaned_str = cleaned.to_string_lossy();  // print-friendly

    println!("{cleaned_str}");

    // 2.  Enforce the sand-box rule
    if !cleaned_str.starts_with("/safedir") {
        println!("out of bounds");
        return File::open("/dev/null")
            .expect("unable to open /dev/null");
    }

    // 3.  Open inside the safe directory (read-write, like Python’s "r+")
    println!("opening file {cleaned_str}");
    OpenOptions::new()
        .read(true)
        .write(true)
        .open(&cleaned)
        .expect("unable to open requested file")
}

fn main() {
    /* ========= first call ========= */
    let mut f = get_file("/safedir/test1.txt");
    let mut buf = String::new();
    f.read_to_string(&mut buf).unwrap();
    println!("{buf}");

    /* ========= second call ========= */
    let mut g = get_file("/dev/rand0");   // will fall back to /dev/null
    let mut buf2 = String::new();
    // reading /dev/null is fine; ignore result length
    let _ = g.read_to_string(&mut buf2);
    println!("{buf2}");
}