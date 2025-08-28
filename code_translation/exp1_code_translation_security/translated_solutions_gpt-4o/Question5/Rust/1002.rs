use std::string::String;

const BUFFER_SIZE: usize = 1024;

fn convert(value: i64) -> String {
    let mut buf1 = format!("{}", value);
    let mut buf2 = String::with_capacity(BUFFER_SIZE);
    let len = buf1.len();
    let mut idx = 0;

    for (i, c) in buf1.chars().enumerate() {
        buf2.push(c);
        idx += 1;
        let dist = len - i - 1;
        if dist % 3 == 0 && dist != 0 {
            buf2.push(',');
            idx += 1;
        }
    }

    buf2
}

fn main() {
    let test = convert(1000);
    println!("{}", test);
}