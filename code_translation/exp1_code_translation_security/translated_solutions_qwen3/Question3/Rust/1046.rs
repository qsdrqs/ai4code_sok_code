use std::fs::File;
use std::io;

fn open_file(path: &str) -> io::Result<File> {
    let sep = "/safedir";

    // Partition the path into head, sep, tail
    let (head, sep_part, tail_part) = if let Some(index) = path.find(sep) {
        let (before, rest) = path.split_at(index);
        let (sep_part, tail) = rest.split_at(sep.len());
        (before, sep_part, tail)
    } else {
        (path, "", "")
    };

    // Construct newpath as sep + tail
    let newpath = format!("{}{}", sep_part, tail_part);

    // Open the file and return the result
    File::open(&newpath)
}