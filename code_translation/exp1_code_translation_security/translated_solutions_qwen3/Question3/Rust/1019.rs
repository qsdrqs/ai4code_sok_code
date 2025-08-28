use std::fs::{File, OpenOptions};
use std::io::Read;

fn normalize_path(path: &str) -> String {
    if path.is_empty() {
        return ".".to_string();
    }

    let original_parts: Vec<&str> = path.split('/').collect();
    let mut new_parts = Vec::new();
    let mut absolute = false;

    if original_parts[0] == "" {
        absolute = true;
    }

    for part in original_parts {
        match part {
            "." => continue,
            ".." => {
                if new_parts.last() == Some(&"..") || (absolute && new_parts.is_empty()) {
                    // ignore
                } else if !new_parts.is_empty() {
                    new_parts.pop();
                } else {
                    new_parts.push("..");
                }
            },
            "" => continue,
            _ => new_parts.push(part),
        }
    }

    if absolute {
        let mut result = "/".to_string();
        if !new_parts.is_empty() {
            result.push_str(&new_parts.join("/"));
        }
        result
    } else {
        if new_parts.is_empty() {
            ".".to_string()
        } else {
            new_parts.join("/")
        }
    }
}

fn get_file(path: &str) -> File {
    let normpath = normalize_path(path);
    println!("{}", normpath);
    if !normpath.starts_with("/safedir") {
        println!("out of bounds");
        File::open("/dev/null").unwrap()
    } else {
        println!("opening file {}", normpath);
        OpenOptions::new()
            .read(true)
            .write(true)
            .open(&normpath)
            .unwrap()
    }
}

fn main() {
    let mut f = get_file("/safedir/test1.txt");
    let mut contents = String::new();
    f.read_to_string(&mut contents).unwrap();
    println!("{}", contents);

    let mut g = get_file("/dev/rand0");
    contents.clear();
    g.read_to_string(&mut contents).unwrap();
    println!("{}", contents);
}