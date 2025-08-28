struct File {
    name: String,
    path: String,
}

fn get_file_path(file: &File) -> (String, String) {
    (file.name.clone(), file.path.clone())
}

fn main() {
    let file = File {
        name: "example.txt".to_string(),
        path: "/path/to/example.txt".to_string(),
    };
    let (file_name, file_path) = get_file_path(&file);
    println!("File Name: {}", file_name);
    println!("File Path: {}", file_path);
}