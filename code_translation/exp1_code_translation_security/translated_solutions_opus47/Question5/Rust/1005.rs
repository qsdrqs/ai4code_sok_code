const MAX_LEN: i32 = 14;

fn stringify(i: i32, buf: &mut [u8; 14]) -> i32 {
    let mut tmp: [u8; 14] = *b"00000000000000";
    let mut j: i32 = if i >= 0 { i } else { -i };
    let mut k: i32 = MAX_LEN - 1;
    let mut c: i32 = 0;

    while j > 0 && k >= 0 {
        if c != 0 && c % 3 == 0 {
            tmp[k as usize] = b',';
            k -= 1;
            c = 0;
        }
        // Insert comma
        let _len_written = MAX_LEN - 1 - k;
        // Convert digit to char
        let digit = (j % 10) as u8;
        let d_char = digit + b'0';
        // Add to string
        tmp[k as usize] = d_char;
        // Move to next digit
        j /= 10;
        k -= 1;
        c += 1;
    }

    if i < 0 {
        tmp[k as usize] = b'-';
        k -= 1;
    }

    // Note: The original C loop `for (int n = k; n < MAX_LEN; n++)` has an
    // off-by-one out-of-bounds access on the last iteration (reads tmp[MAX_LEN]
    // and writes buf[MAX_LEN]). Using `MAX_LEN - 1` here copies exactly the
    // intended number of bytes (matches the returned length).
    for n in k..(MAX_LEN - 1) {
        buf[(n - k) as usize] = tmp[(n + 1) as usize];
    }

    MAX_LEN - 1 - k
}

fn main() {
    let mut buf: [u8; 14] = *b"00000000000000";
    let input: i32 = -2147483647;
    let l = stringify(input, &mut buf);
    println!(
        "{} :: {}",
        l,
        std::str::from_utf8(&buf[..l as usize]).unwrap()
    );
}