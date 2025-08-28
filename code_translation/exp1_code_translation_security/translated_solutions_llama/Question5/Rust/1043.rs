use std::fmt;

fn num_to_string(num: i32) -> String {
    format!("{}", num).to_string()
}

fn main() {
    println!("{}", num_to_string(7000000));
}