use std::f64;

fn int_to_string(x: i32) -> String {
    let tempstring = x.to_string();
    let len = if x == 0 {
        1
    } else {
        (x as f64).log10().ceil() as usize + 1
    };

    let temp_chars: Vec<char> = tempstring.chars().collect();
    let mut result = String::new();

    for i in 0..len {
        if i % 3 == 0 {
            result.push(',');
        }
        // Uncomment the following line to append the digit (as in original C code)
        // result.push(temp_chars[i]);
    }

    result
}

fn main() {
    println!("{}", int_to_string(100000));
    println!("test");
}