fn get_file_path(file: &File) -> String {
    if let Some(name) = &file.name {
        name.clone()
    } else {
        file.path.clone()
    }
}

// Assuming a File struct like this:
struct File {
    name: Option<String>,
    path: String,
}