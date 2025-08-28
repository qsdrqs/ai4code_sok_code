const MAX_LEN: usize = 14;

fn stringify(i: i32, buf: &mut [u8; MAX_LEN]) -> usize {
    let mut tmp = [b'0'; MAX_LEN];
    let mut j = if i >= 0 { i } else { -i };
    let mut k = MAX_LEN - 1;
    let mut c = 0;

    while j > 0 && k >= 0 {
        if c != 0 && c % 3 == 0 {
            tmp[k] = b',';
            k -= 1;
            c = 0;
        }
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

    let len_written = MAX_LEN - 1 - k;
    for n in 0..len_written {
        buf[n] = tmp[k + 1 + n];
    }

    len_written
}

fn main() {
    let mut buf = [b'0'; MAX_LEN];
    let input = -2147483647;
    let l = stringify(input, &mut buf);
    let result = std::str::from_utf8(&buf[..l]).unwrap();
    println!("{} :: {}", l, result);
}