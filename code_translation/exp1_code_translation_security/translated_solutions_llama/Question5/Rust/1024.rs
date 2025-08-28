use std::string::String;

fn int_to_str(n: i32) -> String {
    n.to_string()
}

fn main() {
    let num = 123;
    let str_num = int_to_str(num);
    println!("{}", str_num);
}