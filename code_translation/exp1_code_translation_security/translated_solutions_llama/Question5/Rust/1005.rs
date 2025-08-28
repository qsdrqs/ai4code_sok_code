use std::fmt;

const MAX_LEN: usize = 14;

fn stringify(i: i32, buf: &mut [u8]) -> usize {
    let mut tmp: [u8; MAX_LEN] = *b"00000000000000";
    let mut j = if i >= 0 { i } else { -i };
    let mut k = MAX_LEN - 1;
    let mut c = 0;
    while j > 0 && k > 0 {
        if c != 0 && c % 3 == 0 {
            tmp[k] = b',';
            k -= 1;
            c = 0;
        }
        // Insert comma
        let len_written = MAX_LEN - 1 - k;
        // Convert digit to char
        let digit = (j % 10) as u8;
        let d_char = digit + b'0';
        // Add to string
        tmp[k] = d_char;
        // Move to next digit
        j /= 10;
        k -= 1;
        c += 1;
    }

    if i < 0 {
        tmp[k] = b'-';
        k -= 1;
    }

    for n in (k..MAX_LEN).rev() {
        buf[n - k] = tmp[n];
    }

    MAX_LEN - 1 - k
}

fn main() {
    let mut buf: [u8; MAX_LEN] = *b"00000000000000";
    let input: i32 = -2147483647;
    let l = stringify(input, &mut buf);
    println!("{} :: {}", l, String::from_utf8_lossy(&buf[..l]));
}