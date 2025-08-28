use std::fmt::Write;

fn convert(num: i32) -> String {
    let mut buf = String::new();
    write!(&mut buf, "{}", num).unwrap();
    print!("{}", buf);
    buf
}

fn main() {
    print!("{}", convert(100));
}