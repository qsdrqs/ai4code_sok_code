use std::fs::File;
use std::io;

fn open_file(path: &str) -> io::Result<File> {
    let sep_str = "/safedir";
    // Python's str.partition() returns (head, separator, tail) if found,
    // or (original, "", "") if not found.
    // Rust's equivalent is split_once(), which returns Option<(head, tail)>.
    let (sep, tail) = match path.split_once(sep_str) {
        Some((_head, tail)) => (sep_str, tail),
        None => ("", ""),
    };
    let newpath = format!("{}{}", sep, tail);
    File::open(newpath)
}