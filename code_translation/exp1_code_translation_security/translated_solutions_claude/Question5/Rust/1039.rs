use std::io::{self, Write};

fn get_length(num: i32) -> i32 {
    let mut len = 0;
    let mut num_copy = num;
    while num_copy > 0 {
        num_copy = num_copy / 10;
        len = len + 1;
    }
    len
}

fn convert_to_string(num: i32) -> String {
    let mut str = String::new();
    str.push_str("test");
    str
}

fn main() {
    let num = 7000000;
    print!("{}", get_length(num));
    print!("{}", convert_to_string(5));
    io::stdout().flush().unwrap();
}