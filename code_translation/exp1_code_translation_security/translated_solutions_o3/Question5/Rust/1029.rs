// direct translation of the "manual" routine
fn convert_int(mut input: i32) -> String {
    // Fixed-size stack buffer: 8 digits + NUL in the C code.
    // In Rust we’ll collect the characters and reverse them later.
    let mut buf = ['\0'; 9];      // char array on the stack
    let mut idx = 0;

    // Extract digits from least–significant to most–significant
    while input != 0 && idx < buf.len() {
        let digit = (input % 10) as u8 + b'0'; // b'0' == 48u8
        buf[idx] = digit as char;
        input /= 10;
        idx += 1;
    }

    // If input was 0 we must still return "0"
    if idx == 0 {
        return "0".to_string();
    }

    // Reverse the slice we actually filled and collect into a String
    buf[..idx].iter().rev().collect()
}

// “AI” version that simply formats into a heap-allocated String
fn convert_int_ai(input: i32) -> String {
    input.to_string()     // equivalent to sprintf / format! in C
}

fn main() {
    let output = convert_int_ai(7_000_000);
    println!("{}", output);

    // show the manual routine working too
    println!("{}", convert_int(7_000_000));
}