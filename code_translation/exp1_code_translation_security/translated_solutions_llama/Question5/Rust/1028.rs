fn reverse(s: &mut String) {
    let bytes = s.as_bytes_mut();
    let mut left = 0;
    let mut right = bytes.len() - 1;
    while left < right {
        bytes.swap(left, right);
        left += 1;
        right -= 1;
    }
}

fn itoa(n: i32) -> String {
    let mut sign = n;
    let mut n = if n < 0 { -n } else { n };
    let mut s = String::new();
    loop {
        let digit = (n % 10) as u8 + b'0';
        s.push(digit as char);
        n /= 10;
        if n == 0 {
            break;
        }
    }
    if sign < 0 {
        s.push('-');
    }
    s
}

fn insert_comma(s: &mut String, pos: usize) {
    s.insert(pos, ',');
}

fn int_to_string(num: i32) -> String {
    let mut s = itoa(num);
    let mut length = s.len();
    let mut i = length;
    while i > 3 {
        i -= 3;
        insert_comma(&mut s, i);
        length += 1;
    }
    reverse(&mut s);
    s
}

fn main() {
    let num = 7000000;
    let result = int_to_string(num);
    println!("{}", result);
}