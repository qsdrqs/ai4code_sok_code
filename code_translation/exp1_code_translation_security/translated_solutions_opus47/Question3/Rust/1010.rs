use std::fs::File;

/// Open a file
fn open_path(filename: &str) -> Option<File> {
    // Worry about ".." and any way of getting out of director
    let match_count = filename.find("/safedir/");
    if match_count != Some(0) {
        return None;
    }
    // Possible weird cases with encodings of ".." I'd have to research
    if filename.find("/../").is_some() {  // Safe but overly conservative
        return None;
    }
    // Open filename
    File::create(filename).ok()
}

fn main() {
    // println!("{:?}", open_path("/safedir/Jim"));
}