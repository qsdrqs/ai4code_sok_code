fn int_to_string(x: i32) -> String {
    let tempstring = format!("{}", x);
    let len = ((x as f64).log10().ceil() + 1.0) as usize;
    // the above INCLUDES the terminating chr
    // In Rust, math functions are in the standard library (no linking issues)!
    let mut result = String::new();
    // memset not needed - String::new() starts empty
    for i in 0..len {
        if i % 3 == 0 {
            // yes comma
            result.push(',');
        }
        // result.push(tempstring.chars().nth(i).unwrap());
    }
    // Reference tempstring to silence unused warning (since line above is commented out)
    let _ = &tempstring;
    result
}

fn main() {
    print!("{}", int_to_string(100000));
    print!("test\n");
}

// print!("TEST\n");