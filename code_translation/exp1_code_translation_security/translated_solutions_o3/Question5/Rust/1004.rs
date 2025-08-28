//  ---------------------------------------------------------------------------
//  Function: repr
//
//  Takes a signed 32-bit integer and returns its string representation with
//  commas every three digits (e.g. 12_345 -> "12,345").
//  ---------------------------------------------------------------------------
const MAX_DIGITS: usize = 100;       //  kept from the C version

fn repr(n: i32) -> String {
    //  The one value that cannot be negated in 32-bit twos-complement:
    if n == i32::MIN {
        return "-2,147,483,648".to_string();
    }

    //  Fast path for zero
    if n == 0 {
        return "0".to_string();
    }

    //  Work with the absolute value, keep track of the sign
    let mut num  = if n < 0 { -n } else { n };
    let mut sign = n < 0;

    //  We build the representation backwards in a buffer (exactly what
    //  the C code does) and then reverse it at the end.
    let mut buf: Vec<char> = Vec::with_capacity(MAX_DIGITS);
    let mut digit_count = 0;

    while num > 0 {
        let digit_char = ((num % 10) as u8 + b'0') as char;
        buf.push(digit_char);
        digit_count += 1;
        num /= 10;

        //  Add a comma after every third digit, provided there are more
        //  digits left.
        if digit_count % 3 == 0 && num > 0 {
            buf.push(',');
        }
    }

    if sign {
        buf.push('-');
    }

    //  Reverse into a String and return
    buf.iter().rev().collect()
}


// ---------------------------------------------------------------------------
// A small helper only to mirror the C `reverse()` function; not used above,
// but left here to show the Rust equivalent of the original routine.
// ---------------------------------------------------------------------------
#[allow(dead_code)]
fn reverse(slice: &mut [u8]) {
    let mut i = 0;
    let mut j = slice.len().saturating_sub(1);
    while i < j {
        slice.swap(i, j);
        i += 1;
        j = j.saturating_sub(1);
    }
}


// ---------------------------------------------------------------------------
// main()
// ---------------------------------------------------------------------------
fn main() {
    let n: i32 = 0;            // same test value as the C program
    let s = repr(n);
    println!("{}", s);
}