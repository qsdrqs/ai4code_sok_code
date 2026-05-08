fn main() {
    let num: u32 = 1000000;
    let s = add_commas(num);
    println!("{}", s);
}

fn reverse_str(s: &mut [u8]) {
    let len = s.len();
    for i in 0..len / 2 {
        s.swap(i, len - 1 - i);
    }
}

fn add_commas(mut num: u32) -> String {
    let mut bytes: Vec<u8> = Vec::new();
    let mut count = 0;
    while num != 0 {
        bytes.push((num % 10) as u8 + b'0');
        num /= 10;
        count += 1;
        if count == 3 && num != 0 {
            bytes.push(b',');
            count = 0;
        }
    }
    reverse_str(&mut bytes);
    String::from_utf8(bytes).unwrap()
}