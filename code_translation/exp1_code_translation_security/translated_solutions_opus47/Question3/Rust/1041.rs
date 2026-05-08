use std::error::Error;
use std::fs::File;

fn open_file(path: &str) -> Result<File, Box<dyn Error>> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("Path must start with /safedir".into())
    }
}

fn main() {
    let res1 = open_file("/safedir/random.txt").unwrap();
    println!("{:?}", res1);
    let _res2 = open_file("blah").unwrap();
}