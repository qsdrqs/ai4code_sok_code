// A straight-forward, line-for-line translation of the C version
// (no attempt has been made to “improve” the original algorithm).

const MAX_LEN: usize = 14;

fn stringify(i: i32, buf: &mut [u8; MAX_LEN]) -> usize {
    // Replicates:  char tmp[14] = "00000000000000";
    let mut tmp = [b'0'; MAX_LEN];

    // Absolute value kept as i32 to stay as close to the C code as possible
    let mut j: i32 = if i >= 0 { i } else { -i };
    let mut k: i32 = (MAX_LEN as i32) - 1;
    let mut c: i32 = 0;

    // while (j > 0 && k >= 0)
    while j > 0 && k >= 0 {
        // if (c != 0 && c % 3 == 0) { tmp[k] = ','; … }
        if c != 0 && c % 3 == 0 {
            tmp[k as usize] = b',';
            k -= 1;
            c = 0;
        }

        // int digit = j % 10; char d_char = digit + '0';
        let digit = (j % 10) as u8;
        tmp[k as usize] = digit + b'0';

        // j /= 10;
        j /= 10;
        k -= 1;
        c += 1;
    }

    // if (i < 0) tmp[k] = '-';
    if i < 0 && k >= 0 {
        tmp[k as usize] = b'-';
        k -= 1;
    }

    // Copy the slice [k+1, MAX_LEN) to the caller-supplied buffer
    let start = (k + 1) as usize;
    let mut len = 0;
    for n in start..MAX_LEN {
        buf[n - start] = tmp[n];
        len += 1;
    }

    len
}

fn main() {
    // char buf[14] = "00000000000000";
    let mut buf = [b'0'; MAX_LEN];

    let input: i32 = -2_147_483_647;
    let l = stringify(input, &mut buf);

    // Safe conversion to &str only for the produced length
    let s = std::str::from_utf8(&buf[..l]).unwrap();
    println!("{} :: {}", l, s);
}