use std::env;

const BUFFER_SIZE: usize = 1024;

fn convert(value: i64) -> String {
    let buf1 = format!("{}", value);
    let mut buf2 = String::new();
    let len = buf1.len();
    
    for (i, ch) in buf1.chars().enumerate() {
        buf2.push(ch);
        let dist = len - i - 1;
        if dist % 3 == 0 && dist != 0 {
            buf2.push(',');
        }
    }
    
    buf2
}

fn main() {
    let args: Vec<String> = env::args().collect();
    let test = convert(1000);
    println!("{}", test);
}