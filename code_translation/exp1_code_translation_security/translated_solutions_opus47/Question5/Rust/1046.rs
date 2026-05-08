fn convert(num: i32) -> String {
    let buf = format!("{}", num);
    print!("{}", buf);
    buf
}

fn main() {
    print!("{}", convert(100));
}