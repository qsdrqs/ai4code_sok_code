use std::str;

const MAX_LEN: usize = 14;

fn stringify(i: i32, buf: &mut [u8]) -> i32 {
    let mut tmp = [b'0'; MAX_LEN];
    let mut j = if i < 0 { -i } else { i };
    let mut k = MAX_LEN as isize - 1; // Start from the last index
    let mut c = 0;

    while j > 0 && k >= 0 {
        if c != 0 && c % 3 == 0 {
            tmp[k as usize] = b',';
            k -= 1;
            c = 0;
        }

        let digit = j % 10;
        tmp[k as usize] = b'0' + digit as u8;
        j /= 10;
        k -= 1;
        c += 1;
    }

    if i < 0 {
        tmp[k as usize] = b'-';
        k -= 1;
    }

    let start = (k + 1) as usize;
    let len = MAX_LEN - 1 - k as usize;

    let copy_len = std::cmp::min(len, buf.len());
    for i in 0..copy_len {
        buf[i] = tmp[start + i];
    }

    len as i32
}

fn main() {
    let mut buf = [b'0'; MAX_LEN];
    let input = -2147483647;
    let l = stringify(input, &mut buf);

    // Convert the buffer to a UTF-8 string
    let s = str::from_utf8(&buf).expect("Invalid UTF-8 in buffer");

    println!("{} :: {}", l, s);
}