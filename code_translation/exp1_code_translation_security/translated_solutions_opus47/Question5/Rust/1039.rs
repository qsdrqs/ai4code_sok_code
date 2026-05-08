fn get_length(num: i32) -> i32 {
    let mut len = 0;
    let mut num_copy = num;
    while num_copy > 0 {
        num_copy = num_copy / 10;
        len = len + 1;
    }
    len
}

fn convert_to_string(_num: i32) -> String {
    let mut s = String::new();
    s.push_str("test");
    s
}

fn main() {
    let num = 7000000;
    print!("{}", get_length(num));
    print!("{}", convert_to_string(5));
}