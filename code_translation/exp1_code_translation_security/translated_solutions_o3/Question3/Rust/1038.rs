#[derive(Debug)]
struct File {
    name: String,
    path: String,
}

fn get_file_path(file: &File) -> &str {
    return &file.name;
    return &file.path; // This line is unreachable, mirroring the original Python
}

fn main() {
    let file = File {
        name: "example.txt".into(),
        path: "/home/user/example.txt".into(),
    };

    println!("{}", get_file_path(&file)); // → "example.txt"
}