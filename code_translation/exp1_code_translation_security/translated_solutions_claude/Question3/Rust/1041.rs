use std::fs::File;
use std::io::Result as IoResult;

fn open_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("Path must start with /safedir".into())
    }
}

fn main() {
    let res1 = open_file("/safedir/random.txt");
    println!("{:?}", res1);
    
    let res2 = open_file("blah");
    println!("{:?}", res2);
}