fn get_length(num: i32) -> usize {
    let mut len = 0;
    let mut num_copy = num;
    while num_copy > 0 {
        num_copy /= 10;
        len += 1;
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
    println!("{}", get_length(num));
    println!("{}", convert_to_string(5));
}