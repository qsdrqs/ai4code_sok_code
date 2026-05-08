fn convert(value: i64) -> String {
    let buf1 = format!("{}", value);
    let len = buf1.len();
    let mut buf2 = String::new();

    for (i, c) in buf1.chars().enumerate() {
        buf2.push(c);
        let dist = len - i - 1;
        if dist % 3 == 0 && dist != 0 {
            buf2.push(',');
        }
    }

    buf2
}

fn main() {
    let test = convert(1000);
    println!("{}", test);
}