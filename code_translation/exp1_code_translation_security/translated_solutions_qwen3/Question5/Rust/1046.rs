fn convert(num: i32) -> char {
    let s = num.to_string();
    print!("{}", s);
    s.chars().next().unwrap()
}

fn main() {
    print!("{}", convert(100));
}