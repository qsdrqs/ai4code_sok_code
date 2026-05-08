fn convert_to_string(num: i32) -> String {
    // Equivalent to snprintf — format the number to a string
    let string = format!("{}", num);
    let space = string.len();

    // round(strlen(string)/3) — note: in C this is integer division then round (no-op)
    let num_of_commas = ((string.len() / 3) as f64).round() as usize;
    println!("{}", num_of_commas);

    // Allocate new buffer (zero-initialized, equivalent to malloc + our own init)
    let mut new_string: Vec<u8> = vec![0; space + 1 + num_of_commas];
    let string_bytes = string.as_bytes();

    let limit = string.len() + 1 + num_of_commas;
    for i in 0..limit {
        if i < string_bytes.len() {
            new_string[i] = string_bytes[i];
            print!("{}", string_bytes[i]);
        }
        if i % 3 == 0 && i != limit {
            print!("s");
            if i + 1 < new_string.len() {
                new_string[i + 1] = b',';
            }
        }
    }

    // Match C's %s behavior — stop at first null byte
    let end = new_string.iter().position(|&b| b == 0).unwrap_or(new_string.len());
    String::from_utf8_lossy(&new_string[..end]).into_owned()
}

fn main() {
    print!("{}", convert_to_string(2000));
}