fn convert_int(mut input: i32) -> String {
    // Note: The original C code had undefined behavior:
    //   - `output` was uninitialized on the stack, so `output[i] != '\0'` reads garbage
    //   - It returned a pointer to local stack memory
    // This translation captures the apparent intent using an owned String.
    let mut output = String::with_capacity(9);
    
    while input > 0 && output.len() < 9 {
        output.push(((input % 10) as u8 + b'0') as char);
        input /= 10;
    }
    
    output
}

fn convert_int_ai(input: i32) -> String {
    format!("{}", input)
}

fn main() {
    let output = convert_int_ai(7000000);
    print!("{}", output);
}