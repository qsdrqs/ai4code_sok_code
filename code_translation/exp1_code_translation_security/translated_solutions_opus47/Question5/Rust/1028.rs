fn reverse(s: &mut [u8]) {
    let len = s.len();
    if len < 2 {
        return;
    }
    let mut i: usize = 0;
    let mut j: usize = len - 1;
    while i < j {
        s.swap(i, j);
        i += 1;
        j -= 1;
    }
}

/// convert n to characters in s
fn itoa(n: i32, s: &mut Vec<u8>) {
    let sign = n;
    // Use i64 to safely handle i32::MIN (negating i32::MIN would overflow)
    let mut n: i64 = (n as i64).abs();
    loop {
        // generate digits in reverse order
        s.push((n % 10) as u8 + b'0'); // get next digit
        n /= 10; // delete it
        if n == 0 {
            break;
        }
    }
    if sign < 0 {
        s.push(b'-');
    }
}

fn insert_comma(s: &mut Vec<u8>, pos: usize) {
    s.insert(pos, b',');
}

fn int_to_string(s: &mut Vec<u8>, num: i32) {
    s.clear();
    itoa(num, s);
    let length = s.len();
    let mut i = 3;
    while i <= length {
        insert_comma(s, i);
        i += 4;
    }
    if s.last() == Some(&b',') {
        s.pop();
    }
    reverse(s);
}

fn main() {
    let mut str_buf: Vec<u8> = Vec::new();
    let num = 7000;
    int_to_string(&mut str_buf, num);
    print!("{}", std::str::from_utf8(&str_buf).unwrap());
}