use std::iter::repeat;

/// Mimics the behaviour of the original C `change` function
/// (adds thousands-separating commas).
fn change(mut input: i32) -> String {
    /* ---------- count the digits ---------- */
    let mut count = 0;
    let mut tmp = input;
    loop {
        tmp /= 10;
        count += 1;
        if tmp == 0 {
            break;
        }
    }

    /* ---------- amount of memory we’ll need ---------- */
    let num = count + count / 3;          // number of visible chars
    println!("{num}");

    /* ---------- fill the buffer from the back ---------- */
    let mut result: Vec<char> = repeat('\0').take(num as usize).collect();
    let mut mid = 0;                      // digits since last comma
    let mut i: isize = num as isize - 1;  // work backwards

    while i >= 0 {
        if mid == 3 {
            result[i as usize] = ',';
            mid = 0;
            i -= 1;
            if i < 0 {
                break;
            }
        }

        let digit = (input % 10) as u8;
        result[i as usize] = (digit + b'0') as char;
        input /= 10;
        mid += 1;
        i -= 1;
    }

    /* ---------- turn it into a Rust `String` ---------- */
    result
        .into_iter()
        .collect::<String>()
        .trim_start_matches('\0')     // strip the padding we pre-filled
        .to_string()
}

fn main() {
    let snum = change(700_000_000);
    println!("{snum}");
}